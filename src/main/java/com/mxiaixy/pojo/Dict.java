package com.mxiaixy.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Mxia on 2017/2/21.
 */
public class Dict implements Serializable {


    private Integer id;
    private String dictName;
    private Integer dictId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }
}
