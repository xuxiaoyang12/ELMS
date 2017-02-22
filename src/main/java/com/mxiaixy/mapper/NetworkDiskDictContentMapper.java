package com.mxiaixy.mapper;

import com.mxiaixy.pojo.DictContent;

import java.util.List;

/**
 * Created by Mxia on 2017/2/21.
 */
public interface NetworkDiskDictContentMapper {
    List<DictContent> findDictContentByDictId(Integer dictId);
}
