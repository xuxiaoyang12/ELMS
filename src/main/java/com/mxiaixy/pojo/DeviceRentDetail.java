package com.mxiaixy.pojo;


import java.io.Serializable;

/**
 * Created by Mxia on 2017/2/18.
 */

public class DeviceRentDetail implements Serializable {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceUnit() {
        return deviceUnit;
    }

    public void setDeviceUnit(String deviceUnit) {
        this.deviceUnit = deviceUnit;
    }

    public Float getDevicePrice() {
        return devicePrice;
    }

    public void setDevicePrice(Float devicePrice) {
        this.devicePrice = devicePrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getRentId() {
        return rentId;
    }

    public void setRentId(Integer rentId) {
        this.rentId = rentId;
    }

    private String deviceName;
    private String deviceUnit;
    private Float devicePrice;
    private Integer num;
    private Float totalCost;
    private Integer rentId;
}
