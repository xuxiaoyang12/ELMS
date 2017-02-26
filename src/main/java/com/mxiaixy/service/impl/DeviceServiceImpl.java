package com.mxiaixy.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxiaixy.dto.DeviceRentDto;
import com.mxiaixy.dto.wx.TextMessage;
import com.mxiaixy.exception.ServiceException;
import com.mxiaixy.mapper.*;
import com.mxiaixy.pojo.*;
import com.mxiaixy.service.DeviceService;
import com.mxiaixy.service.WeiXinService;
import com.mxiaixy.util.SerialNumberUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Mxia on 2017/1/23.
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceRentMapper deviceRentMapper;
    @Autowired
    private DeviceRentDetailMapper deviceRentDetailMapper;
    @Autowired
    private DeviceRentDocMapper deviceRentDocMapper;
    @Autowired
    private DeviceWorkerTypeMapper deviceWorkerTypeMapper;
    @Autowired
    private FinanceMapper financeMapper;
    @Autowired
    private WeiXinService weiXinService;

    @Value("${upload.path}")
    private String filePath;
    /**
     * 查询所有设备信息
     * @return
     */
    @Override
    public List<Device> findAllDevice() {

        List<Device> deviceList = deviceMapper.findAllDevice();


        return deviceList;
    }

    /**
     * 添加新设备
     * @param device
     */
    @Override
    public void addDevice(Device device) {

        deviceMapper.addDevice(device);
    }

    /**
     * 通过id查询设备
     * @param id
     * @return
     */
    @Override
    public Device findById(String id) {

        return  deviceMapper.findById(id);
    }

    /**]
     * 新建租赁合同  并返回序列号
     * @param deviceRentDto 合同数据json
     * @return 合同 序列码
     */
    @Override
    @Transactional
    public String  addDeviceRent(DeviceRentDto deviceRentDto) {
        //判断库存量是否足够


        //1.保存合同表单
        DeviceRent deviceRent = new DeviceRent();
        deviceRent.setDeviceName(deviceRentDto.getDeviceName());
        deviceRent.setDeviceUnit(deviceRentDto.getDeviceUnit());
        deviceRent.setDevicePrice(deviceRentDto.getDevicePrice());
        deviceRent.setNum(deviceRentDto.getNum());
        deviceRent.setDays(deviceRentDto.getDays());
        deviceRent.setCreateTime(deviceRentDto.getCreateTime());
        deviceRent.setRebackTime(deviceRentDto.getRebackTime());
        deviceRent.setCompany(deviceRentDto.getCompany());
        deviceRent.setCompanyPhone(deviceRentDto.getCompanyPhone());
        deviceRent.setCorportion(deviceRentDto.getCorportion());
        deviceRent.setAddress(deviceRentDto.getAddress());
        deviceRent.setPhone(deviceRentDto.getPhone());
        deviceRent.setTotalCost(deviceRentDto.getTotalCost());
        deviceRent.setFirstCost(deviceRentDto.getFirstCost());
        deviceRent.setLastCost(deviceRentDto.getLastCost());
        String serialNumer = SerialNumberUtil.getSerialNumber();
        deviceRent.setSerialNumber(serialNumer);

        deviceRentMapper.saveDeviceRent(deviceRent);
        //2.保存合同详情

        //判断库存量是够足够
        Device device = deviceMapper.findByName(deviceRentDto.getDeviceName());

        if(device !=null){
            if(device.getTotal() > deviceRentDto.getNum()){
                DeviceRentDetail deviceRentDetail = new DeviceRentDetail();
                deviceRentDetail.setDeviceName(deviceRentDto.getDeviceName());
                deviceRentDetail.setDeviceUnit(deviceRentDto.getDeviceUnit());
                deviceRentDetail.setDevicePrice(deviceRentDto.getDevicePrice());
                deviceRentDetail.setNum(deviceRentDto.getNum());
                deviceRentDetail.setTotalCost(deviceRentDto.getTotalCost());
                deviceRentDetail.setRentId(deviceRent.getId());

                deviceRentDetailMapper.saveDeviceRentDetail(deviceRentDetail);
            }else{
                throw new ServiceException(device.getName()+"库存不足");
            }
        }



        //3.保存合同文件


        List<DeviceRentDto.FileArrayBean> fileArrayBeanList = deviceRentDto.getFileArray();

        List<DeviceRentDoc> deviceRentDocList = Lists.newArrayList();
        for(DeviceRentDto.FileArrayBean fileArrayDoc : fileArrayBeanList){
            DeviceRentDoc deviceRentDoc = new DeviceRentDoc();
            deviceRentDoc.setNewFileName(fileArrayDoc.getNewName());
            deviceRentDoc.setSourceFileName(fileArrayDoc.getSourceName());
            deviceRentDoc.setRentId(deviceRent.getId());
            deviceRentDocList.add(deviceRentDoc);

        }
        if(deviceRentDocList != null){
            deviceRentDocMapper.saveDeviceRentDoc(deviceRentDocList);
        }


        //4，保存财务流水
        //获取财务流水对象
        Finance finance = new Finance();
        finance.setName(deviceRentDto.getCompany()+" 预付款");//设置财务流水名称
        finance.setSerialNumber(new DateTime().toString("YYYYmmDD")+ RandomStringUtils.randomNumeric(4));
        finance.setType(Finance.IN_TYPE);//金额类型
        finance.setMoney(deviceRentDto.getFirstCost());//预付款
        finance.setModule("设备租赁模块");//模块
        finance.setState(Finance.DEFAULT_STATE);
        finance.setCreateTime(deviceRentDto.getCreateTime());
        //TODO 新增登陆模块后添加
        finance.setCreateUser("tom");
        finance.setConfirmUser("tom");
        finance.setConfirmDate(new DateTime().toString("YYYY-MM-DD"));//财务流水创建时间
        finance.setRemark("预付款");
        finance.setDeviceSerialNumber(serialNumer);
        financeMapper.saveFinance(finance);

        //通过微信企业号发送推送消息 提示财务部成员确认流水
        TextMessage textMessage = new TextMessage();
        TextMessage.TextBean textBean = new TextMessage.TextBean();
        textMessage.setToparty("7   ");//部门列表
        textBean.setContent("设备租赁模块添加一笔财务流水[预付款]，请确认");
        textMessage.setText(textBean);

        try {
            weiXinService.sendText(textMessage);
        } catch (IOException e) {
            throw new ServiceException("发送文本消息异常",e);
        }


        return String.valueOf(deviceRent.getSerialNumber());//返回对象序列号
    }

    /**
     * 通过序列号查询合同对象
     * @param serialNumber 序列号
     * @return 合同对象
     */
    @Override
    public DeviceRent findDeviceRentBySerialNumber(String serialNumber) {

        return deviceRentMapper.findBySerialNumber(serialNumber);
    }

    /**
     * 通过合同id 查询合同详情
     * @param id
     * @return
     */
    @Override
    public DeviceRentDetail findDeviceDetailById(Integer id) {

        return deviceRentDetailMapper.findByRendId(id);
    }

    /**
     * 通过合同id查询合同文件
     * @param id
     * @return
     */
    @Override
    public DeviceRentDoc findDeviceDocById(Integer id) {

        return deviceRentDocMapper.findByRendId(id);
    }

    /**
     * 查询所有工种
     * @return
     */
    @Override
    public List<WorkerType> findWorkerTypeList() {
        return deviceWorkerTypeMapper.findAllWorkerType();
    }

    @Override
    public WorkerType findWorkerTypeById(Integer id) {
        return deviceWorkerTypeMapper.findWorkerTypeById(id);
    }

    /**
     * 通过合同文件id 获取文件输入流 下载文件
     * @param id 合同文件id
     * @return
     */
    @Override
    public InputStream downloadFile(Integer id) throws IOException {
        DeviceRentDoc deviceRentDoc = deviceRentDocMapper.findById(id);
        if(deviceRentDoc == null){
            return null;
        }else{
            File file = new File(new File(filePath),deviceRentDoc.getNewFileName());
            if(file.exists()){
                return new FileInputStream(file);
            }else{
                return null;
            }
        }


    }

    @Override
    public DeviceRentDoc findDeviceDocByDocId(Integer docId) {
        return deviceRentDocMapper.findById(docId);
    }

    @Override
    public List<DeviceRentDoc> findAllDeviceRentDocByRenId(Integer rentId) {
        return deviceRentDocMapper.findAllByRentId(rentId);
    }

    @Override
    public void downloadZipFile(DeviceRent rent, ZipOutputStream zipOutputStream) throws IOException {
        //通过合同对象id找到合同文件列表
        List<DeviceRentDoc> deviceRentDocList = deviceRentDocMapper.findAllByRentId(rent.getId());
        for(DeviceRentDoc doc:deviceRentDocList){
            //循环出每个合同文件
            ZipEntry zipEntry = new ZipEntry(doc.getSourceFileName());//参数是用来声明zip包中子文件的名字
            zipOutputStream.putNextEntry(zipEntry);

            InputStream inputStream = downloadFile(doc.getId());
            IOUtils.copy(inputStream,zipOutputStream);
            inputStream.close();

        }
        zipOutputStream.closeEntry();
        zipOutputStream.flush();
        zipOutputStream.close();
    }

    /**
     * 通过开始和结束行数 查询所有合同对象
     * @param queryParam
     * @return
     */
    @Override
    public List<DeviceRent> findDeviceRentByQueryParam(Map<String, Object> queryParam){

        return deviceRentMapper.findAllByQueryParam(queryParam);
    }

    @Override
    public Long countOfDeviceRent() {

        return deviceRentMapper.countOfDeviceRent();
    }

    @Override
    public DeviceRent findDeviceRentById(Integer id) {

        return deviceRentMapper.findById(id);
    }

    @Override
    @Transactional //添加事物
    public void changerState(Integer id) {
        //改变状态为已完成
        Map<String,Object> map = Maps.newHashMap();
        map.put("state","已完成");
        map.put("id",id);
        deviceRentMapper.changState(map);
        //

        //向财务模块添加尾款记录
        //通过合同对象id找到合同对象的序列号
        DeviceRent deviceRent = deviceRentMapper.findById(id);
        //通过序列号向财务流水更改状态
        Finance finance = financeMapper.findFinanceByDeviceSerialNumber(deviceRent.getSerialNumber());
        finance.setState(Finance.OK_STATE);
        financeMapper.update(finance);
        //并添加一个新的流水
        Finance newFinance = new Finance();
        newFinance.setName(deviceRent.getCompany()+"尾款");
        newFinance.setSerialNumber(new DateTime().toString("YYYYMMDD")+ RandomStringUtils.randomNumeric(4));
        newFinance.setType(finance.getType());
        newFinance.setMoney(deviceRent.getLastCost());
        newFinance.setModule(finance.getModule());
        newFinance.setCreateTime(finance.getCreateTime());
        newFinance.setCreateUser(finance.getCreateUser());
        newFinance.setConfirmUser(finance.getConfirmUser());
        newFinance.setConfirmDate(new DateTime().toString("YYYY-MM-DD"));
        newFinance.setRemark("");
        newFinance.setDeviceSerialNumber(finance.getDeviceSerialNumber());
        newFinance.setState(finance.getState());
        financeMapper.saveFinance(newFinance);

        //通过微信企业号发送推送消息 提示财务部成员确认流水
        TextMessage textMessage = new TextMessage();
        TextMessage.TextBean textBean = new TextMessage.TextBean();
        textMessage.setToparty("7");//部门列表
        textBean.setContent("设备租赁模块添加一笔财务流水[尾款]，请确认");
        textMessage.setText(textBean);

        try {
            weiXinService.sendText(textMessage);
        } catch (IOException e) {
            throw new ServiceException("发送文本消息异常",e);
        }



    }

    /**
     * 通过id 查询工种
     * @param id
     * @return
     */
    @Override
    public WorkerType findWorkerById(Integer id) {
        WorkerType workerType = null;
        if(id !=null){
           workerType = deviceWorkerTypeMapper.findWorkerTypeById(id);
        }else{
            throw new ServiceException("工种不存在或已被删除");
        }
        return workerType;
    }
}
