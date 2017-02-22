package com.mxiaixy.service;

import com.mxiaixy.dto.DeviceRentDto;
import com.mxiaixy.pojo.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * Created by Mxia on 2017/1/23.
 */
public interface DeviceService {
    List<Device> findAllDevice();

    void addDevice(Device device);

    Device findById(String id);

    String addDeviceRent(DeviceRentDto deviceRentDto);

    DeviceRent findDeviceRentBySerialNumber(String serialNumber);

    DeviceRentDetail findDeviceDetailById(Integer id);

    DeviceRentDoc findDeviceDocById(Integer id);

    List<WorkerType> findWorkerTypeList();

    WorkerType findWorkerTypeById(Integer integer);

    InputStream downloadFile(Integer id) throws IOException;

    DeviceRentDoc findDeviceDocByDocId(Integer docId);

    List<DeviceRentDoc> findAllDeviceRentDocByRenId(Integer id);

    void downloadZipFile(DeviceRent id, ZipOutputStream zipOutputStream) throws IOException;

    List<DeviceRent> findDeviceRentByQueryParam(Map<String, Object> queryParam);

    Long countOfDeviceRent();

    DeviceRent findDeviceRentById(Integer id);

    void changerState(Integer id);
}
