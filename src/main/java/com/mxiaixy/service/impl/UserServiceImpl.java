package com.mxiaixy.service.impl;

import com.mxiaixy.mapper.UserMapper;
import com.mxiaixy.pojo.User;
import com.mxiaixy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mxia on 2017/1/23.
 */
@Service
public class UserServiceImpl implements UserService {

    //注入所需要的mapper类
    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean login(User user) {
        //验证账号和密码是否匹配
        //通过账号查询数据
        User user1 = userMapper.findByEmail(user.getEmail());
        if(user1 !=null) {
            if(user.getPassword().equals(user1.getPassword())){
                return true;
            }
        }
        return false;
    }
}
