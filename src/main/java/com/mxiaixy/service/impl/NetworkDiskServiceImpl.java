package com.mxiaixy.service.impl;

import com.mxiaixy.mapper.NetworkDiskDictContentMapper;
import com.mxiaixy.mapper.NetworkDiskDictMapper;
import com.mxiaixy.pojo.Dict;
import com.mxiaixy.pojo.DictContent;
import com.mxiaixy.service.NetworkDiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mxia on 2017/2/21.
 */
@Service
public class NetworkDiskServiceImpl implements NetworkDiskService {


    @Autowired
    private NetworkDiskDictMapper networkDiskDictMapper;
    @Autowired
    private NetworkDiskDictContentMapper networkDiskDictContentMapper;

    /**
     * 通过文件夹id查询文件夹下文件夹
     * @param dictId
     * @return
     */
    @Override
    public List<Dict> findDictByDictId(Integer dictId) {

        return networkDiskDictMapper.findDictByDictId(dictId);
    }

    /**
     *
     * 通过文件夹id查询文件夹下文件
     * @param dictId
     * @return
     */
    @Override
    public List<DictContent> findDictContentByDictId(Integer dictId) {
        return networkDiskDictContentMapper.findDictContentByDictId(dictId);
    }
}
