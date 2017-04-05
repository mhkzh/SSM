package com.zh.service;

import java.util.Set;

import com.zh.model.ShiroUser;
import com.zh.model.User;

public interface ShiroUserService {
	
	    public User getUserByIdTest(int userId);  
	    
	    public ShiroUser findUserByUsername(String userName);
	    
	    public Set<String> findRoles(String userName);
	    
	    public Set<String> findPermissions(String userName);
	    

}
