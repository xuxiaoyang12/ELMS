package com.mxiaixy.controller;

import com.mxiaixy.exception.NotFoundException;
import com.mxiaixy.pojo.Role;
import com.mxiaixy.pojo.User;
import com.mxiaixy.service.UserService;
import com.mxiaixy.service.WeiXinService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by Mxia on 2017/1/23.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    //自动注入所需要的逻辑类
    @Autowired
    private UserService userService;
    @Autowired
    private WeiXinService weiXinService;




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

    /**
     * 显示添加用户
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String addUser(Model model){
        //查询所有角色
        List<Role> roleList = userService.finaAllRole();

        model.addAttribute("roleList",roleList);
        return "/user/add";
    }

    @PostMapping("/add")
    public String addUser(User user,Integer[] roleIds,RedirectAttributes redirectAttributes){
        userService.addUser(user,roleIds);
        redirectAttributes.addFlashAttribute("message","操作成功");
        //请求转发到用户列表
        return "redirect:/user/list" ;
    }

    /**
     * 获取账户列表
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model){
        //查找所有用户
        List<User> userList = userService.findALlUser();
        model.addAttribute("userList",userList);

        return "/user/list";
    }

    /**
     * 编辑账户信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit")
    public String edit(Integer id,Model model){
        //通过账号id  查询账户信息并编辑账户信息
        User user = userService.findUserById(id);
        if(user == null){
            throw new NotFoundException();
        }else{
            //查询角色信息
            List<Role> roleList = userService.findAllRole();
            model.addAttribute("user",user);
            model.addAttribute("roleList",roleList);
            return "/user/edit";
        }

    }

    /**
     * 编辑已有账户信息
     * @param user
     * @param roleIds
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/edit")
    public String edit(User user,Integer[] roleIds,RedirectAttributes redirectAttributes){
        //编辑账户信息
        //1.通过账户id更改账户信息
        userService.updateUserById(user,roleIds);
        redirectAttributes.addFlashAttribute("message","操作成功");

        return "redirect:/user/list";
    }

    @GetMapping("/del")
    public String del(Integer id,RedirectAttributes redirectAttributes){

        System.out.println(id);
        //通过id删除用户数据
        userService.delUser(id);
        redirectAttributes.addFlashAttribute("message","操作成功");
        return "redirect:/user/list";
    }

}
