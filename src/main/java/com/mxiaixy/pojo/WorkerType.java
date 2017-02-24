package com.mxiaixy.pojo;


import java.io.Serializable;

/**
 * Created by Mxia on 2017/2/18.
 */
public class WorkerType implements Serializable {

    private Integer id;
    private String workName;
    private float workPrice;
    private Integer workNum;

    public Integer getWorkNum() {
        return workNum;
    }

    public void setWorkNum(Integer workNum) {
        this.workNum = workNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public float getWorkPrice() {
        return workPrice;
    }

    public void setWorkPrice(float workPrice) {
        this.workPrice = workPrice;
    }

}
