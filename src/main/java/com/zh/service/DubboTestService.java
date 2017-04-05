package com.zh.service;

import com.zh.model.User;

/**
 * Dubbo消费者接口
 * @author Administrator
 *
 */
public interface DubboTestService {
	
	   public int insert(User record);

}
