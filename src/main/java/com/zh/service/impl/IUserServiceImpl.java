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
	    //����
	    @Transactional  
		public int insert(User record) {
			 return this.userDao.insert(record);
//			 throw new RuntimeException("test"); //�׳�unchecked�쳣����������ع�  
		}  
	    
	   

}
