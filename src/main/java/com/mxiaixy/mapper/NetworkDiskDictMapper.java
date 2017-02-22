package com.mxiaixy.mapper;

import com.mxiaixy.pojo.Dict;

import java.util.List;

/**
 * Created by Mxia on 2017/2/21.
 */
public interface NetworkDiskDictMapper {
    List<Dict> findDictByDictId(Integer dictId);
}
