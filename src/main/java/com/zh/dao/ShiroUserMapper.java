package com.zh.dao;

import java.util.Set;

import com.zh.model.ShiroUser;

public interface ShiroUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShiroUser record);

    int insertSelective(ShiroUser record);

    ShiroUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShiroUser record);

    int updateByPrimaryKey(ShiroUser record);
    
    ShiroUser findUserByUsername(String userName);
    
    Set<String>  findRoles(String userName);
    
    Set<String>  findPermissions(String userName);
    
}