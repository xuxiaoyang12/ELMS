package com.mxiaixy.service;

import com.mxiaixy.pojo.User;
import org.springframework.stereotype.Service;

/**
 * Created by Mxia on 2017/1/23.
 */
public interface UserService {


    Boolean login(User user);
}
