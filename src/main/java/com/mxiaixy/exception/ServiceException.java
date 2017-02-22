package com.mxiaixy.exception;



/**
 * Created by Mxia on 2017/2/20.
 */
public class ServiceException extends RuntimeException {

    public ServiceException(){}

    public ServiceException(String message){
        super(message);
    }
    public ServiceException(Throwable th){
        super(th);
    }
    public ServiceException(String message , Throwable th){
        super(message,th);
    }
}
