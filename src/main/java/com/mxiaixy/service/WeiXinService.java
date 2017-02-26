package com.mxiaixy.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.mxiaixy.dto.wx.User;
import com.mxiaixy.exception.ServiceException;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mxia on 2017/2/24.
 */
@Service
public class WeiXinService {
    /**
     * 日志
     */
    Logger logger = LoggerFactory.getLogger(WeiXinService.class);

    /**
     * 静态主动调用微信公众号的url  get请求
     */
    private static final String ACCESS_TOKEN_URL="https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={0}&corpsecret={1}";
    /**
     * 创建用户时的接口凭证
     */
    private static final String CREATE_USER_URL="https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token={0}";

    private static final String UPDATE_USER_URL="https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token={0}";

    private static final String DEL_USER_URL="https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token={0}&userid={1}";

    @Value("${wx.token}")
    private String token;

    @Value("${wx.aeskey}")
    private String aeskey;
    @Value("${wx.corpid}")
    private String corpId;
    @Value("${wx.secret}")
    private String sectet;

    //使用代理对象
    /**
     * 通过代理获取AccessToken 并放在缓存中7200s 过期之后自动获取
     */
    private LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(10)
            .expireAfterAccess(7200, TimeUnit.SECONDS)
            .build(new CacheLoader<String , String >() {
                @Override
                public String load(String s) throws Exception {
                    //获取请求的url
                    String url = MessageFormat.format(ACCESS_TOKEN_URL,corpId,sectet);

                    //使用请求代理
                    //获取代理对象
                    OkHttpClient okHttpClient = new OkHttpClient();
                    //向微信发出get请求
                    Request request = new Request.Builder().url(url).build();
                    //接受响应
                    Response response = okHttpClient.newCall(request).execute();
                    String result = response.body().string();
                    //关闭响应
                    response.close();
                    //把响应的json转换为集合map
                    Map<String,Object> map = new Gson().fromJson(result, HashMap.class);
                    //判断接收值是否正确
                    /**正确返回
                     * {
                     "access_token": "accesstoken000001",
                     "expires_in": 7200
                     *}
                     *错误返回
                     *{
                     "errcode": 43003,
                     "errmsg": "require https"
                     }
                     *
                     */
                    if(map.containsKey("errcode")){
                        logger.error("获取微信access_token异常",map.get("errcode"));
                        throw new ServiceException("获取微信access_token异常"+map.get("errcode"));
                    }else{
                        return map.get("access_token").toString();
                    }

                }


            });



    /**
     * 微信初始化
     * @param msg_signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    public String init(String msg_signature, String timestamp, String nonce, String echostr) {

        try {

            WXBizMsgCrypt crypt = new WXBizMsgCrypt(token,aeskey,corpId);
            return crypt.VerifyURL(msg_signature,timestamp,nonce,echostr);
        } catch (AesException e) {
            throw new ServiceException("微信初始化异常！");
       }

    }

    /**
     * 获取accessToken
     * @return
     */
    public String getAccessToken(){
        try {
            return cache.get("");
        } catch (ExecutionException e) {
            throw new ServiceException("获取AccessToken异常",e);
        }
    }

    /**
     * 创建用户并更新到微信
     * @param user
     * @throws IOException
     */
    public  void createUser(User user) throws IOException {
        //获取微信创建成员的url
        String url = MessageFormat.format(CREATE_USER_URL,getAccessToken());

        //获取传值对象并转化为jsoN
        String json = new Gson().toJson(user);
        //设置传值的字符格式和编码
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json);
        //获取代理对象
        OkHttpClient httpClient = new OkHttpClient();
        //发出post请求
        Request request =new Request.Builder()
                .url(url)
                .post(body)
                .build();
        //响应
        Response response = httpClient.newCall(request).execute();
        //获取响应的值
        String result = response.body().string();

        //把响应的值转化为map
        Map<String,Object> map = new Gson().fromJson(result,HashMap.class);
        //判断接收值是否正确
        if(!"0.0".equals(map.get("errcode").toString())){
            logger.error("用户创建失败",map.get("errcode"));
            throw new ServiceException("用户创建失败"+map.get("errcode"));

        }
        logger.info(result+"成功");
    }


    /**
     * 同步微信更新成员
     * @param user
     * @throws IOException
     */
    public void updateUser(User user) throws IOException {
        //获取更新成员的url
        String url = MessageFormat.format(UPDATE_USER_URL,getAccessToken());

        //获取传值对象并转换为json
        String json = new Gson().toJson(user);
        //设置传入值得格式和字符编码
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json);
        //获取请求代理
        OkHttpClient okHttpClient = new OkHttpClient();
        //发送请求
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        //接收响应
        Response response = okHttpClient.newCall(request).execute();
        //获取响应值
        String result = response.body().string();

        //转换为map集合
        Map<String ,Object> map =new Gson().fromJson(result,HashMap.class);
        //判断是否更新成功
        //String errcode = map.get("errcode").toString();转化为字符串
        if(!"0.0".equals(map.get("errcode").toString())){
            logger.error("更新微信公众号成员失败{}",map.get("errcode"));
            throw new ServiceException("更新微信公众号成员失败"+map.get("errcode"));
        }

    }

    public void delUser(String userId) throws IOException {
        //获取删除成员的url
        String url = MessageFormat.format(DEL_USER_URL,getAccessToken(),userId);
        //发出get请求
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = httpClient.newCall(request).execute();
        String result = response.body().string();

        //转换为map
        Map<String ,Object> map = new Gson().fromJson(result,HashMap.class);

        String errcode = map.get("errcode").toString();
        if(!"0.0".equals(errcode)){
            logger.error("删除微信成员不成功{}",map.get("errcode"));
            throw new ServiceException("删除微信成员不成功"+map.get("errcode"));
        }
        logger.info("微信公共号成员删除成功");
    }
}
