package com.mxiaixy.controller;

import com.mxiaixy.dto.wx.User;
import com.mxiaixy.service.WeiXinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mxia on 2017/2/24.
 */
@Controller
@RequestMapping("/wx")
public class WeiXinController {

    Logger logger = LoggerFactory.getLogger(WeiXinController.class);

    @Autowired
    private WeiXinService weiXinService;
    /**
     * 微信公众平台初始化
     * @return
     */
    @GetMapping("/init")
    @ResponseBody
    public String weixin(String msg_signature,String timestamp,String nonce,String echostr ){
        logger.info("{}-{}-{}-{}",msg_signature,timestamp,nonce,echostr);//判断是否接收到微信传过来的值
        return weiXinService.init(msg_signature,timestamp,nonce,echostr);
    }

    @GetMapping("/hhh")
    public String jjjj(){

        Integer[] roleIds =new Integer[]{1,2};
        //创建新的用户
        User user = new User();
        user.setUserid("liuxiaoshuai");
        user.setName("刘小帅");
        user.setMobile("15239033932");
        user.setDepartment(Arrays.asList(roleIds));
        try {
            weiXinService.createUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
