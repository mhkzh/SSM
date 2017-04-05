package com.zh.controller;
import com.zh.model.ShiroUser;
import com.zh.service.ShiroUserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
/**
 * 后台Controller
 */
@Controller
@RequestMapping("/shiroUser")
public class ShiroUserController {
    @Resource
    private ShiroUserService t_userService ;
    @RequestMapping("/loginAdmin")
    public String login(ShiroUser user, Model model){
        Subject subject = SecurityUtils.getSubject() ;
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword()) ;
        try {
            subject.login(token);
            return "admin" ;
        }catch (Exception e){
            //这里将异常打印关闭是因为如果登录失败的话会自动抛异常
//            e.printStackTrace();
            model.addAttribute("error","用户名或密码错误") ;
            return "../../login" ;
        }
    }
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }
    @RequestMapping("/student")
    public String student(){
        return "admin" ;
    }
    @RequestMapping("/teacher")
    public String teacher(){
        return "admin" ;
    }
}