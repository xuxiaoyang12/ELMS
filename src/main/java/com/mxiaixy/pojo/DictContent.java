package com.mxiaixy.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Mxia on 2017/2/21.
 */
@Data
public class DictContent implements Serializable {
    private Integer id;
    private String dictContentName;
    private String dictContent;
    private Integer dictId;


}
