package com.javen.testmybatis;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.zh.producer.MessageProducer;

/**
 * 功能概要：
 * 
 * @author zhanghao
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
@WebAppConfiguration 
public class MessageTest  {

    @Resource  
    private MessageProducer messageProducer; 
    
    private MockMvc mvc;  
    
    @Autowired  
    private WebApplicationContext wac;  
    
    @Before
    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();  
    }
    
    
//@Test
//rabbitMq测试
	public void should_send_a_amq_message() throws Exception {
       int a = Integer.MAX_VALUE;
//       while (a > 0) {
//    	   messageProducer.sendMessage("Hello World:" + a--);
//    	   try {
//    		   //暂停一下，好让消息消费者去取消息打印出来
//               Thread.sleep(3000);
//           } catch (InterruptedException e) {
//               e.printStackTrace(); 
//           }
    
//	   }
       messageProducer.sendMessage("Hello World:");
	}
	
    /**
    整个过程：
    1、mockMvc.perform执行一个请求；
    2、MockMvcRequestBuilders.get("/hello")构造一个请求
    3、ResultActions.andExpect添加执行完成后的断言
    4、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情，比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
    5、ResultActions.andReturn表示执行完成后返回相应的结果。
     * @throws Exception 
    **/
    @Test
    public void getHello() throws Exception{
    //perform 执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
    mvc.perform(MockMvcRequestBuilders.get("/user/showUser").accept(MediaType.APPLICATION_JSON).param("id", "1"))
    			//andExpect：添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确；
                .andExpect(MockMvcResultMatchers.status().isOk())
                //andDo：添加ResultHandler结果处理器，比如调试时打印结果到控制台；
                .andDo(MockMvcResultHandlers.print())
                //andReturn：最后返回相应的MvcResult；然后进行自定义验证/进行下一步的异步处理；
                .andReturn();
    }
	
}
