package com.zh.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;


/**
 * 功能概要：消费接收
 *  
 */
@Service
public class MessageConsumer implements MessageListener {
	

	public void onMessage(Message message) {
		System.out.println("I'm eclipse MavenDemo consumer："+message);
	}

}
