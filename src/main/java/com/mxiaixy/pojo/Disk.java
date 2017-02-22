package com.mxiaixy.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Mxia on 2017/2/21.
 */

public class Disk implements Serializable {

    public static final String FILE_TYPE = "file";
    public static final String FOLDER_TYPE = "folder";

    private Integer id;
    private String sourceName;
    private String name;
    private String createTime;
    private String createUser;
    private String type;
    private String size;

    private Integer fid;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
