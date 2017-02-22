package com.mxiaixy.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Mxia on 2017/1/23.
 */
public class Device implements Serializable {
    private Integer id;
    private String name;
    private Integer total;
    private Integer currentTotal;
    private String unit;
    private Double price;
    private Timestamp createTime;
    private Timestamp modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCurrentTotal() {
        return currentTotal;
    }

    public void setCurrentTotal(Integer currentTotal) {
        this.currentTotal = currentTotal;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", currentTotal=" + currentTotal +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
