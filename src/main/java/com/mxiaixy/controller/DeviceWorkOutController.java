package com.mxiaixy.controller;

import com.mxiaixy.mapper.DeviceMapper;
import com.mxiaixy.pojo.WorkerType;
import com.mxiaixy.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Mxia on 2017/2/18.
 */
@Controller
@RequestMapping("/device/workOut")
public class DeviceWorkOutController {

    @Autowired
    public DeviceService deviceService;


    @GetMapping("/add")
    public String deviceWorkOutAdd(Model model){
        //获取工种列表
       List<WorkerType> workerTypeList =  deviceService.findWorkerTypeList();

       System.out.println(workerTypeList);
        model.addAttribute("workerTypeList",workerTypeList);
        return "/device/workOut/add";
    }

    @PostMapping("/add")
    @ResponseBody
    public WorkerType findWorkerType(String id){

        return deviceService.findWorkerTypeById(Integer.valueOf(id));
    }
}
