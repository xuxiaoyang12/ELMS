package com.mxiaixy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mxia on 2017/2/22.
 */
@Controller
@RequestMapping("/device")
public class DeviceController {


    @GetMapping("/menu")
    public String menu(){
        return "/device/menu";
    }
}

