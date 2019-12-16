package org.jscucc.cn.service;

import org.jscucc.cn.entity.ResultMsg;
import org.jscucc.cn.entity.UserInfo;

public interface UserService {
	
	public ResultMsg addUser(UserInfo user);
	
	public ResultMsg findByIdCard(String idCard);
}
