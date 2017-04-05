package com.zh.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zh.dao.ShiroUserMapper;
import com.zh.model.ShiroUser;
import com.zh.model.User;
import com.zh.service.ShiroUserService;

@Service("shiroUserService")  
public class ShiroUserServiceImpl implements ShiroUserService{
    @Resource  
    private ShiroUserMapper shiroUserDao;  

	public User getUserByIdTest(int userId) {
		return null;
	}

	public ShiroUser findUserByUsername(String userName) {
		return shiroUserDao.findUserByUsername(userName);
	}

	public Set<String>  findRoles(String userName) {
		return shiroUserDao.findRoles(userName);
	}

	public Set<String>  findPermissions(String userName) {
		return shiroUserDao.findPermissions(userName);
	}

}
