package com.mxiaixy.service;

import com.mxiaixy.pojo.Role;
import com.mxiaixy.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mxia on 2017/1/23.
 */
public interface UserService {


    Boolean login(User user);


    List<Role> finaAllRole();

    void addUser(User user, Integer[] roleIds);


    List<User> findALlUser();

    User findUserById(Integer id);

    List<Role> findAllRole();

    void updateUserById(User user, Integer[] roleIds);

    void delUser(Integer userId);
}
