package com.mxiaixy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Mxia on 2017/3/10.
 */
@Controller
public class LoginController {

    /**
     * 登录shrio入口
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String login(){
        return "user/login";
    }

    /**
     * 密码验证
     * @param name
     * @param password
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String login(String name, String password,
                        RedirectAttributes redirectAttributes){
        //使用shiro方式进行登录
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(new UsernamePasswordToken(name, password));
            return "redirect:/device/menu";
        }catch(AuthenticationException e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误");
            return "redirect:/";
        }
    }
   @RequestMapping("/user/logout")
    public String logout(RedirectAttributes redirectAttributes){

        //安全退出
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message","已安全退出");
        return "redirect:/";

   }
}
