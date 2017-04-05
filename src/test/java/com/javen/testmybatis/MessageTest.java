package com.javen.testmybatis;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zh.producer.MessageProducer;

/**
 * 功能概要：
 * 
 * @author zhanghao
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class MessageTest  {

    @Resource  
    private MessageProducer messageProducer; 
    
	@Test
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
}
