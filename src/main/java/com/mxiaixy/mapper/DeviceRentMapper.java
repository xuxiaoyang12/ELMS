package com.mxiaixy.mapper;

import com.mxiaixy.dto.DeviceRentDto;
import com.mxiaixy.pojo.*;
import com.mxiaixy.pojo.DeviceRentDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by Mxia on 2017/2/18.
 */
public interface DeviceRentMapper {
    void saveDeviceRent(DeviceRent deviceRent);

    DeviceRent findById(Integer integer);

    List<DeviceRent> findAllByQueryParam(Map<String, Object> queryParam);

    Long countOfDeviceRent();

    DeviceRent findBySerialNumber(String serialNumber);

    void changState(Map<String,Object> map);
}
