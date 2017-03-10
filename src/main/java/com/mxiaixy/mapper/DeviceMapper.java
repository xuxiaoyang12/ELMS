package com.mxiaixy.mapper;

import com.mxiaixy.pojo.Device;
import com.mxiaixy.pojo.User;

import java.util.List;

/**
 * Created by Mxia on 2017/1/23.
 */
public interface DeviceMapper {
    List<Device> findAllDevice();

    void addDevice(Device device);

    Device findById(String id);

    Device findByName(String name);
}
