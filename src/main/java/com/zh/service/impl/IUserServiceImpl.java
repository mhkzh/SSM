package com.zh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zh.dao.UserMapper;
import com.zh.model.User;
import com.zh.service.IUserService;


	@Service("userService")  
	public class IUserServiceImpl implements IUserService {  
	    @Resource  
	    private UserMapper userDao;  
	    
	    public User getUserByIdTest(int userId) {  
	        // TODO Auto-generated method stub  
	        return this.userDao.selectByPrimaryKey(userId);  
	    }
	    //事务
	    @Transactional  
		public int insert(User record) {
			 return this.userDao.insert(record);
//			 throw new RuntimeException("test"); //抛出unchecked异常，触发事物，回滚  
		}  
	    
	   

}
