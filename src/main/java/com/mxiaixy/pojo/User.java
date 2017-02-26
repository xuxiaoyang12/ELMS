package com.mxiaixy.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mxia on 2017/1/23.
 */
@Data
public class User implements Serializable {

    private Integer id;
    private String name ;
    private String password;
    private String mobile;
    private List<Role> roleList;


    /**
     * 提供一个获取用户角色的方法
     * @return
     */
    public String getRoleNames(){
        //创建一个集合
        List<String> roleNames = new ArrayList<>();
        //循环出当前用户用户大所有角色
        for(Role role:roleList){
            roleNames.add(role.getRoleName());
        }
        //使用字符串连加吧 集合中的角色加成一串字符串
        StringBuffer sb = new StringBuffer();
        //循环出角色连加
        for(String roleName : roleNames){
            sb.append(roleName).append("  ");
        }
        return sb.toString();
    }





}
