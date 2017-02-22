package com.mxiaixy.dto;

import java.util.List;

/**
 * Created by Mxia on 2017/2/18.
 */
public class DeviceRentDto {


    /**
     * deviceName : 请选择设备
     * deviceUnit :
     * devicePrice :
     * createTime : 2017-02-18
     * rebackTime :
     * num :
     * days :
     * corportion :
     * company :
     * address :
     * companyPhone :
     * phone :
     * totalCost :
     * firstCost :
     * lastCost :
     * fileArray : [{"sourceName":"20128179545026116.jpg","newName":"0ce1822a-0faa-49a1-8030-e38c67e985b5.jpg"}]
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
    private List<FileArrayBean> fileArray;

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

    public List<FileArrayBean> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<FileArrayBean> fileArray) {
        this.fileArray = fileArray;
    }

    public static class FileArrayBean {
        /**
         * sourceName : 20128179545026116.jpg
         * newName : 0ce1822a-0faa-49a1-8030-e38c67e985b5.jpg
         */

        private String sourceName;
        private String newName;

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public String getNewName() {
            return newName;
        }

        public void setNewName(String newName) {
            this.newName = newName;
        }
    }
}
