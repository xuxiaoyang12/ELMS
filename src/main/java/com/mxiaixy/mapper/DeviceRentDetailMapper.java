package com.mxiaixy.mapper;

import com.mxiaixy.pojo.DeviceRentDetail;

/**
 * Created by Mxia on 2017/2/18.
 */
public interface DeviceRentDetailMapper {
    void saveDeviceRentDetail(DeviceRentDetail deviceRentDetail);

    DeviceRentDetail findByRendId(Integer id);
}
