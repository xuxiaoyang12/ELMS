package com.mxiaixy.mapper;

import com.mxiaixy.pojo.Disk;

import java.util.List;

/**
 * Created by Mxia on 2017/2/21.
 */
public interface PanMapper {
    List<Disk> findDiskByFid(Integer fid);

    void addFolder(Disk disk);


    Disk findDiskById(Integer id);

    void delDiskById(Integer id);

    List<Disk> findAllDisk();

    void delBatchById(List<Integer> idList);
}
