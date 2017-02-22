package com.mxiaixy.mapper;

import com.mxiaixy.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Mxia on 2017/1/23.
 */
public interface UserMapper {


    User findByEmail(String email);
}
