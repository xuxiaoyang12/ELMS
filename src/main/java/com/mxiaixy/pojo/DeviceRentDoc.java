package com.mxiaixy.pojo;


import java.io.Serializable;

/**
 * Created by Mxia on 2017/2/18.
 */
public class DeviceRentDoc implements Serializable {

    private Integer id;
    private String newFileName;
    private String sourceFileName;
    private Integer rentId;

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public Integer getRentId() {
        return rentId;
    }

    public void setRentId(Integer rentId) {
        this.rentId = rentId;
    }


}
