package com.mxiaixy.controller;

import com.google.common.collect.Maps;
import com.mxiaixy.pojo.Leave;
import com.mxiaixy.pojo.User;
import com.mxiaixy.service.LeaveWorkFlow;
import org.activiti.engine.ActivitiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

/**
 * Created by Mxia on 2017/3/9.
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {

    Logger logger = LoggerFactory.getLogger(LeaveController.class);
    @Autowired
    private LeaveWorkFlow leaveWorkFlow;


    /**
     * 申请流程
     * @return
     */
    @GetMapping("/apply")
    public String apply(){


        return "activiti/leave/leave-apply";
    }

    /**
     * 启动流程
     * @return
     */
    @PostMapping("/start")
    public String startProcess(Leave leave, Model model, RedirectAttributes redirectAttributes){

        //获取当前的用户
        //TODO 未加shiro  模拟获得了当前用户
        User user = new User();
        user.setId(3);
        user.setName("小强");
        user.setPassword("000000");
        //获取流程的key
        String processDefinitionKey="leave";
        //TODo 获取部门经理的id  暂时设置为
        String departmentId = "5";
        Map<String,Object> map = Maps.newHashMap();
        map.put("departmentId",departmentId);

        //启动启动流程
        try {
            leaveWorkFlow.processStart(leave, user, processDefinitionKey, map);

        }catch(ActivitiException e){
            logger.error("KEY为{}：流程启动异常",processDefinitionKey);
            model.addAttribute("message","流程启动失败");
        }

        redirectAttributes.addFlashAttribute("message","流程启动成功");

        return "activiti/leave/leave-apply";


    }
}
