package com.zh.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.zh.model.User;
import com.zh.redis.cache.PutCache;
import com.zh.service.DubboTestService;
import com.zh.service.IUserService;


@Controller  
@RequestMapping("/user") 
// /user/**
public class UserController {
	private static Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource  
    private IUserService userService; 
    
//	@Resource 
	//这里其实是由dubbo远程调用得到的对象,如果zookeeper未开启，在这里注入会出现异常
//	private DubboTestService dubboTestService;

	
    // /user/test?id=1
    @PutCache(name="newsList",value="com.zh.UserController.showUser(String).123")  
    @RequestMapping(value="/test",method=RequestMethod.GET)  
    public String test(HttpServletRequest request,Model model){  
        int userId = Integer.parseInt(request.getParameter("id"));  
        System.out.println("userId:"+userId);
        User user=null;
        if (userId==1) {
             user = new User();  
             user.setAge(11);
             user.setId(1);
             user.setPassword("123");
             user.setUserName("javen");
        }
       
        log.debug(user.toString());
        model.addAttribute("user", user);  
        return "index";  
    }
    
    
    // /user/showUser?id=1
    // 从请求里面获取参数
    //@GetCache(name="newsList",value="com.zh.UserController.showUser(String).123")  
    @RequestMapping(value="/showUser",method=RequestMethod.GET)  
    @ResponseBody
    public String toIndex(HttpServletRequest request,Model model){  
        int userId = Integer.parseInt(request.getParameter("id"));  
        System.out.println("userId:"+userId);
        User user = this.userService.getUserByIdTest(userId);  
        log.debug(user.toString());
        String json = JSON.toJSONString(user);
        return json;
        //model.addAttribute("user", user);  
        //return "showUser";  
    }  
    
    // /user/showUser2?id=1
    //用注解@RequestParam直接获取参数名为id的值。
    @RequestMapping(value="/showUser2",method=RequestMethod.GET)  
    public String toIndex2(@RequestParam("id") String id,Model model){  
        int userId = Integer.parseInt(id);  
        System.out.println("userId:"+userId);
        User user = this.userService.getUserByIdTest(userId);  
        log.debug(user.toString());
        model.addAttribute("user", user);  
        return "showUser";  
    }  
    
    
    // /user/showUser3/{id}
    // @pathVariable 从路径中获取变量，把路径当做变量
    @RequestMapping(value="/showUser3/{id}",method=RequestMethod.GET)  
    public String toIndex3(@PathVariable("id")String id,Map<String, Object> model){  
        int userId = Integer.parseInt(id);  
        System.out.println("userId:"+userId);
        User user = this.userService.getUserByIdTest(userId);  
        log.debug(user.toString());
        model.put("user", user);  
        return "showUser";  
    }  
    
 // /user/{id}
    @RequestMapping(value="/{id}",method=RequestMethod.GET)  
    public @ResponseBody User getUserInJson(@PathVariable String id,Map<String, Object> model){  
        int userId = Integer.parseInt(id);  
        System.out.println("userId:"+userId);
        User user = this.userService.getUserByIdTest(userId);  
        log.info(user.toString());
        return user;  
    }  
    
    // /user/{id}
    @RequestMapping(value="/jsontype/{id}",method=RequestMethod.GET)  
    public ResponseEntity<User>  getUserInJson2(@PathVariable String id,Map<String, Object> model){  
        int userId = Integer.parseInt(id);  
        System.out.println("userId:"+userId);
        User user = this.userService.getUserByIdTest(userId);  
        log.info(user.toString());
        return new ResponseEntity<User>(user,HttpStatus.OK);  
    } 
    
    //文件上传、
    @RequestMapping(value="/upload")
    public String showUploadPage(){
        return "user_admin/file";
    }
    
    @RequestMapping(value="/doUpload",method=RequestMethod.POST)
    public String doUploadFile(@RequestParam("file")MultipartFile file) throws IOException{
        if (!file.isEmpty()) {
            log.info("Process file:{}",file.getOriginalFilename());
        }
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File("E:\\",System.currentTimeMillis()+file.getOriginalFilename()));
        return "succes";
    }
    
    //跨域问题
    @RequestMapping(value = "/jsonpInfo",method = { RequestMethod.GET })
    @ResponseBody
    public Object jsonpInfo(String callback,Integer userId) throws IOException {
        User user = userService.getUserByIdTest(userId);
        JSONPObject jsonpObject = new JSONPObject(callback,user) ;
        return jsonpObject ;
    }
    
    //测试dubbo调用
//    @RequestMapping(value = "/insert",method = { RequestMethod.GET })
//    @ResponseBody
//    public void insert(){
//    	User user = new User();
//    	user.setAge(12);
//    	user.setPassword("123456");
//    	user.setUserName("测试dubbo");
//    	dubboTestService.insert(user);
//    }

}
