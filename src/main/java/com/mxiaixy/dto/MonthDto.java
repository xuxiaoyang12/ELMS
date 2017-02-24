package com.mxiaixy.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Mxia on 2017/2/24.
 */
@Data
public class MonthDto implements Serializable {


    private String date;
    private String type ;
    private String money;
    private String remark;

}
