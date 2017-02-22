package com.mxiaixy.controller;

import com.mxiaixy.pojo.User;
import com.mxiaixy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Mxia on 2017/1/23.
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    //自动注入所需要的逻辑类
    @Autowired
    private UserService userService;




    @RequestMapping(value = "/login",method = RequestMethod.GET )
    public String login(){
        return ("/user/login");
    }

    @RequestMapping(value = "login" ,method = RequestMethod.POST)
    public String login(User user , Model model , RedirectAttributes redirectAttributes){
        Boolean result = userService.login(user);
        if(result){
            //登录成功 重定向到首页
           return ("redirect:/device/menu");
        }
        redirectAttributes.addFlashAttribute("message","账号或密码错误！");
        //使用重定向到当前的url
        return("redirect:/user/login");
    }



}
