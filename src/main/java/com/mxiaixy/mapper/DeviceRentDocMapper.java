package com.mxiaixy.mapper;

import com.mxiaixy.pojo.DeviceRentDoc;

import java.util.List;

/**
 * Created by Mxia on 2017/2/18.
 */
public interface DeviceRentDocMapper {
    void saveDeviceRentDoc(List<DeviceRentDoc> deviceRentDocList);

    DeviceRentDoc findByRendId(Integer rentId);

    DeviceRentDoc findById(Integer id);

    List<DeviceRentDoc> findAllByRentId(Integer rentId);
}
