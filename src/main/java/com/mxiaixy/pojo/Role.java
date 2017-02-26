package com.mxiaixy.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Mxia on 2017/2/25.
 */
@Data
public class Role implements Serializable {


    private Integer id;
    private String viewName;
    private String roleName;


}
