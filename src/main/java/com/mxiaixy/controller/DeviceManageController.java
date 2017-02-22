package com.mxiaixy.controller;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.mxiaixy.pojo.Device;
import com.mxiaixy.service.DeviceService;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mxia on 2017/2/15.
 */
@Controller
@RequestMapping("/device/manage")
public class DeviceManageController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String deviceList(Model model){
        //获取设备列表
        List<Device> deviceList = deviceService.findAllDevice();
        if(deviceList==null){

        }
        model.addAttribute("deviceList",deviceList);
        return("device/manage/list");
    }
    @RequestMapping(value = "/ajaxList",method = RequestMethod.GET)
    public void deviceList(HttpServletResponse resp) throws IOException {
        //获取设备列表
        List<Device> deviceList = deviceService.findAllDevice();
        if(deviceList==null){

        }
        Map<String, Object> map = Maps.newHashMap();
        map.put("deviceList",deviceList);

        resp.setContentType("application/json,charset=UTF-8");

        PrintWriter out = resp.getWriter();

        //把集合转化为json用gson库
        out.print(new Gson().toJson(map));
        out.flush();
        out.close();
    }

    /**
     * 通过设备id查询设备信息
     * @param id
     * @return
     */
    @RequestMapping(value="/findByName",method = RequestMethod.POST)
    @ResponseBody
    public  Device findByName(String id){

        //通过设备名称查找设备信息
        return deviceService.findById(id);

    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String deviceAdd(){
        return ("device/manage/add");
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String deviceAdd(Device device, RedirectAttributes redirectAttributes){
        //添加设备
        deviceService.addDevice(device);



        return "redirect:/device/manage/list";
    }
}
