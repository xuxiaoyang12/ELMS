package com.mxiaixy.controller;

import com.mxiaixy.dto.AjaxResult;
import com.mxiaixy.exception.ServiceException;
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

import java.rmi.server.ServerCloneException;
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
    public WorkerType findWorkerType(Integer id){


        return deviceService.findWorkerTypeById(Integer.valueOf(id));
    }

    /**
     * 显示劳务新增页
     * @param model
     * @return
     */
    @GetMapping("/new")
    public String deviceWorkoutAdd(Model model){
        //获取工种列表
        List<WorkerType> workerTypeList =  deviceService.findWorkerTypeList();

        System.out.println(workerTypeList);
        model.addAttribute("workerTypeList",workerTypeList);
        return "/device/workOut/new";
    }

    @GetMapping("/worker.json")
    @ResponseBody
    public AjaxResult getWorkerInformation(Integer id){

        try {
            WorkerType workerType = deviceService.findWorkerById(id);
            return new AjaxResult(workerType);

        }catch (ServiceException e){
            return new AjaxResult(AjaxResult.ERROR,e.getMessage());
        }


    }

}
