package com.mxiaixy.mapper;

import com.mxiaixy.pojo.Role;
import com.mxiaixy.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mxia on 2017/1/23.
 */
public interface UserMapper {


    User findByName(String name);

    void save(User user);

    void saveRoleAndUser(@Param("userId") Integer id,@Param("roleId") Integer id1);

    List<User> findAllUser();

    User findUserById(Integer id);

    List<Role> findAllRole();

    void delUserAndRoleByUserId(Integer id);

    void update(User user);

    void delUserById(Integer userId);

    User findByUsername(String username);
}
