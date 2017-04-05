package com.zh.producer;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * 功能概要：消息产生,提交到队列中去
 * 
 */
@Service
public class MessageProducer {

	@Resource
	private AmqpTemplate amqpTemplate;

	public void sendMessage(Object message){
	  System.out.println("我是生产者，我发送了消息"+message);
	  amqpTemplate.convertAndSend("queueTestKey",message);
	}
}
