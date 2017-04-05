package com.zh.producer;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * ���ܸ�Ҫ����Ϣ����,�ύ��������ȥ
 * 
 */
@Service
public class MessageProducer {

	@Resource
	private AmqpTemplate amqpTemplate;

	public void sendMessage(Object message){
	  System.out.println("���������ߣ��ҷ�������Ϣ"+message);
	  amqpTemplate.convertAndSend("queueTestKey",message);
	}
}
