package com.zh.service;

import com.zh.model.User;

public interface IUserService {
	
	    public User getUserByIdTest(int userId);  
	    
	    //����spring����Ļع�
	    public int insert(User record);

}
