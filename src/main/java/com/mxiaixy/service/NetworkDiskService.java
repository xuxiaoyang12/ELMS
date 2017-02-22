package com.mxiaixy.service;

import com.mxiaixy.pojo.Dict;
import com.mxiaixy.pojo.DictContent;

import java.util.List;

/**
 * Created by Mxia on 2017/2/21.
 */
public interface NetworkDiskService {


    List<Dict> findDictByDictId(Integer dictId);

    List<DictContent> findDictContentByDictId(Integer dictId);
}
