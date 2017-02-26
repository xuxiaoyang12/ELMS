package com.mxiaixy.service.impl;

import com.mxiaixy.exception.ServiceException;
import com.mxiaixy.mapper.RoleMapper;
import com.mxiaixy.mapper.UserMapper;
import com.mxiaixy.pojo.Role;
import com.mxiaixy.pojo.User;
import com.mxiaixy.service.UserService;
import com.mxiaixy.service.WeiXinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mxia on 2017/1/23.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 日志
     */
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    //注入所需要的mapper类
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private WeiXinService weiXinService;

    @Override
    public Boolean login(User user) {
        //验证账号和密码是否匹配
        //通过账号查询数据
        User user1 = userMapper.findByName(user.getName());
        if(user1 !=null) {
            if(user.getPassword().equals(user1.getPassword())){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Role> finaAllRole() {


        return roleMapper.findAllRole();
    }

    /**
     * 添加新用户
     * @param user
     * @param roleIds
     */
    @Override
    @Transactional//添加事物
    public void addUser(User user, Integer[] roleIds) {
        //保存用户数据
        userMapper.save(user);
        //保存用户和角色的关系
        if(roleIds!=null){
            for(Integer id :roleIds){
                userMapper.saveRoleAndUser(user.getId(),id);
            }
        }
        //添加用户到微信企业号端
        com.mxiaixy.dto.wx.User wxUser = new com.mxiaixy.dto.wx.User();
        wxUser.setUserid(user.getId().toString());
        wxUser.setName(user.getName());
        wxUser.setMobile(user.getMobile());
        wxUser.setDepartment(Arrays.asList(roleIds));

        try {
            weiXinService.createUser(wxUser);
        } catch (IOException e) {
            throw new ServiceException("保存用户失败",e);
        }

    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> findALlUser() {
        return userMapper.findAllUser();
    }

    /**
     * 通过id查询账号信息
     * @param id
     * @return
     */
    @Override
    public User findUserById(Integer id) {


        return  userMapper.findUserById(id);
    }

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<Role> findAllRole() {
        return userMapper.findAllRole();
    }

    /**
     * 编辑用户信息
     * @param user
     * @param roleIds
     */
    @Override
    @Transactional
    public void updateUserById(User user, Integer[] roleIds) {
        //删除用户角色关系表
        userMapper.delUserAndRoleByUserId(user.getId());
        //添加新的关系表
        if(roleIds !=null){
            for(Integer roleId : roleIds){
                //添加关系表
                userMapper.saveRoleAndUser(user.getId(),roleId);
            }
            //更新用户数据
            userMapper.update(user);
        }

        //同步更新微信公众平台 TODO
        com.mxiaixy.dto.wx.User wxUser = new com.mxiaixy.dto.wx.User();
        wxUser.setUserid(user.getId().toString());
        wxUser.setName(user.getName());
        wxUser.setMobile(user.getMobile());
        wxUser.setDepartment(Arrays.asList(roleIds));

        //更新
        try {
            weiXinService.updateUser(wxUser);

        } catch (IOException e) {
            throw new ServiceException("更新用户失败",e);
        }

    }

    /**
     * 删除用户
     * @param userId
     */
    @Override
    @Transactional
    public void delUser(Integer userId) {
        //1.删除用户角色关系表
        userMapper.delUserAndRoleByUserId(userId);
        //2.删除用户信息
        userMapper.delUserById(userId);
        //3.删除微信公众号成员信息

        try {
            weiXinService.delUser(userId.toString());
            logger.info("删除微信公众号成员成功");
        } catch (IOException e) {
            throw new ServiceException("删除微信公众号成员失败",e);
        }


    }
}
