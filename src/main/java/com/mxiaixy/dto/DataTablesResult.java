package com.mxiaixy.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Mxia on 2017/2/20.
 */
@Data
@AllArgsConstructor
public class DataTablesResult {

    private String draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private Object data;


}
