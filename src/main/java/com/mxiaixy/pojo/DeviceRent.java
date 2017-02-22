package com.mxiaixy.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Mxia on 2017/2/18.
 */
public class DeviceRent implements Serializable {


    /**
     * deviceName : 502
     * deviceUnit : 台
     * devicePrice : 100
     * createTime : 2017-02-18T03:12:30.777Z
     * rebackTime : 2017-02-24
     * num : 4
     * days : 6
     * corportion :
     * company :
     * address :
     * companyPhone :
     * phone :
     * totalCost : 2400
     * firstCost : 720
     * lastCost : 1680
     */

    private Integer id;
    private String deviceName;
    private String deviceUnit;
    private float devicePrice;
    private String createTime;
    private String rebackTime;
    private int num;
    private int days;
    private String corportion;
    private String company;
    private String address;
    private String companyPhone;
    private String phone;
    private float totalCost;
    private float firstCost;
    private float lastCost;
    private String state;

    private String serialNumber;//序列号

    public String getState() {

        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSerialNumber() {

        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

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

    public float getDevicePrice() {
        return devicePrice;
    }

    public void setDevicePrice(float devicePrice) {
        this.devicePrice = devicePrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRebackTime() {
        return rebackTime;
    }

    public void setRebackTime(String rebackTime) {
        this.rebackTime = rebackTime;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getCorportion() {
        return corportion;
    }

    public void setCorportion(String corportion) {
        this.corportion = corportion;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public float getFirstCost() {
        return firstCost;
    }

    public void setFirstCost(float firstCost) {
        this.firstCost = firstCost;
    }

    public float getLastCost() {
        return lastCost;
    }

    public void setLastCost(float lastCost) {

        this.lastCost = lastCost;
    }

    @Override
    public String toString() {
        return "DeviceRent{" +
                "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                ", deviceUnit='" + deviceUnit + '\'' +
                ", devicePrice=" + devicePrice +
                ", createTime='" + createTime + '\'' +
                ", rebackTime='" + rebackTime + '\'' +
                ", num=" + num +
                ", days=" + days +
                ", corportion='" + corportion + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", companyPhone='" + companyPhone + '\'' +
                ", phone='" + phone + '\'' +
                ", totalCost=" + totalCost +
                ", firstCost=" + firstCost +
                ", lastCost=" + lastCost +
                '}';
    }
}
