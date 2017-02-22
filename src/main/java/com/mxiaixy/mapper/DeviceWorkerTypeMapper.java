package com.mxiaixy.mapper;

import com.mxiaixy.pojo.WorkerType;

import java.util.List;

/**
 * Created by Mxia on 2017/2/18.
 */
public interface DeviceWorkerTypeMapper {
    List<WorkerType> findAllWorkerType();

    WorkerType findWorkerTypeById(Integer id);
}
