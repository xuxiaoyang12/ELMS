package com.mxiaixy.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mxia on 2017/3/9.
 */
@Controller
@RequestMapping("/process")
public class ProcessController {



    @GetMapping("/apply")
    public String applyProcess(){


        return "activiti/process/processList";
    }
}
