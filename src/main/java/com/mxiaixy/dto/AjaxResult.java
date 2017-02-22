package com.mxiaixy.dto;

import lombok.Data;

/**
 * 创建数据传输对象
 * Created by Mxia on 2017/2/17.
 */
@Data
public class AjaxResult {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    private String status ;//状态
    private String message;//错误信息
    private Object data;//发送的数据

    //重写他的构造方法
    public  AjaxResult(String status,String message){
        this.status = status;
        this.message = message;
    }

    public AjaxResult(Object data){
        this.status = SUCCESS;
        this.data = data;
    }
}
