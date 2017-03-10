package com.mxiaixy.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Mxia on 2017/3/9.
 */
@Data
public class Leave extends Process implements Serializable {


    private String endTime;
    private String startTime;
    private String realityStartTime;
    private String realityEndTime;
    private String leaveType;
    private String reason;


}
