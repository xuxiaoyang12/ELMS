package com.mxiaixy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;

import java.io.Serializable;

/**
 * Created by Mxia on 2017/2/23.
 */
@Data
public class Finance implements Serializable{

    public static final String DEFAULT_STATE = "未确认";
    public static final String OK_STATE = "已确认";
    public static final String IN_TYPE ="收入";
    public static final String OUT_TYPE ="支出";

    private Integer id;
    private String serialNumber;
    private String name;
    private String type;
    private String state;
    private Float money;
    private String createTime;
    private String createUser;
    private String confirmUser;
    private String confirmDate;
    private String remark;
    private String module;
    private String deviceSerialNumber;


}
