package com.mxiaixy.controller;

import com.google.common.collect.Maps;
import com.mxiaixy.dto.AjaxResult;
import com.mxiaixy.dto.DataTablesResult;
import com.mxiaixy.dto.DeviceRentDto;
import com.mxiaixy.exception.NotFoundException;
import com.mxiaixy.exception.ServiceException;
import com.mxiaixy.pojo.Device;
import com.mxiaixy.pojo.DeviceRent;
import com.mxiaixy.pojo.DeviceRentDetail;
import com.mxiaixy.pojo.DeviceRentDoc;
import com.mxiaixy.service.DeviceService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * Created by Mxia on 2017/2/15.
 */
@Controller
@RequestMapping("/device/rent")
public class DeviceRentController {



    @Autowired
    public DeviceService deviceService;

    @RequestMapping("/add")
    public String deviceOutAdd(Model model) {
        //获取所有的设备
        List<Device> deviceList = deviceService.findAllDevice();
        model.addAttribute("deviceList",deviceList);
        return("device/rent/add");
    }
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult deviceOutAdd(@RequestBody DeviceRentDto deviceRentDto){
        //保存租赁合同表单 并获取合同对象序列号
        String serialNumber = null;
        try {
            serialNumber= deviceService.addDeviceRent(deviceRentDto);
        }catch (ServiceException e){
            return new AjaxResult(AjaxResult.ERROR,e.getMessage());
        }
        return new AjaxResult(serialNumber) ;
    }
    @RequestMapping("/list")
    public String deviceOutList() {
        return("device/rent/list");
    }

    /**
     * 租赁合同详情页获取进入
     * @return
     */
    @GetMapping("/{serialNumber:\\d+}")
    public String deviceDetail(@PathVariable String serialNumber,Model model){

        //查询合同对象
        DeviceRent deviceRent = deviceService.findDeviceRentBySerialNumber(serialNumber);
        if(deviceRent != null){
            //查询合同详情
            DeviceRentDetail deviceRentDetail = deviceService.findDeviceDetailById(deviceRent.getId());
            //查询合同文件列表
            List<DeviceRentDoc> deviceRentDocList = deviceService.findAllDeviceRentDocByRenId(deviceRent.getId());
            model.addAttribute("deviceRent",deviceRent);
            model.addAttribute("deviceRentDetail",deviceRentDetail);
            model.addAttribute("deviceRentDocList",deviceRentDocList);

        }
        return "/device/rent/detail";
    }

    @GetMapping("/doc")
    public void downloadFileDoc(Integer id,HttpServletResponse response) throws IOException {

        //获取合同文件输入流
        InputStream inputStream = deviceService.downloadFile(id);
        if(inputStream == null){
            throw  new NotFoundException();
        }else{
            DeviceRentDoc doc = deviceService.findDeviceDocByDocId(id);

            //将下载文件标记为二进制
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            //更改下载文件名称
            String fileName = doc.getSourceFileName();
            fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");

            //获取输出流
            OutputStream outputStream = response.getOutputStream();
            IOUtils.copy(inputStream,outputStream);

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
    }

    @GetMapping("/doc/zip")
    public void downloadZipFile(Integer id,HttpServletResponse response) throws IOException {
        DeviceRent rent = deviceService.findDeviceRentById(id);
        if(rent == null){
            throw new NotFoundException();
        }else{
        //将文件下载标注为二进制
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
       //更改文件的下载名称
            String fileName = rent.getCompany() + ".zip";//声明下载的zip文件名称
            //转换字符编码
            fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition","attachment;fileName=\""+fileName+"\"");

        //获取输出流
            OutputStream outputStream = response.getOutputStream();
        //使用装饰者模式
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
         //下载zip文件
            deviceService.downloadZipFile(rent,zipOutputStream);
        }
    }

    @GetMapping("/load")
    @ResponseBody
    public DataTablesResult load(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");

        Map<String,Object>  queryParam = Maps.newHashMap();
        queryParam.put("start",start);
        queryParam.put("length",length);

        //查询所有的合同对象
        List<DeviceRent> deviceRentList = deviceService.findDeviceRentByQueryParam(queryParam);

        //查询列表的个数
        Long count = deviceService.countOfDeviceRent();

        return new DataTablesResult(draw,count,count,deviceRentList) ;
    }

    @PostMapping("/state/change")
    @ResponseBody
    public AjaxResult changState(Integer id){

        //通过id 更该状态
        deviceService.changerState(id);

        return new AjaxResult(AjaxResult.SUCCESS);
    }
}
