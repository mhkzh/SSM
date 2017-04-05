package com.zh.service;

import com.zh.model.User;

public interface IUserService {
	
	    public User getUserByIdTest(int userId);  
	    
	    //测试spring事务的回滚
	    public int insert(User record);

}
