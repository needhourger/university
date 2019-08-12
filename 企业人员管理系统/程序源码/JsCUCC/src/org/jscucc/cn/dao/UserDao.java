package org.jscucc.cn.dao;

import org.jscucc.cn.entity.UserInfo;

public interface UserDao {
	
	//添加用户
	public void addUser(UserInfo user);

	//根据身份证查询用户信息
	public UserInfo findByIdCard(String idCard);
}
