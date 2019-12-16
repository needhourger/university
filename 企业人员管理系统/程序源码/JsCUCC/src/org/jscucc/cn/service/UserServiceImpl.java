package org.jscucc.cn.service;

import javax.annotation.Resource;

import org.jscucc.cn.dao.UserDao;
import org.jscucc.cn.entity.ResultMsg;
import org.jscucc.cn.entity.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	
	
	@Override
	public ResultMsg addUser(UserInfo user) {
		
		ResultMsg rs = new ResultMsg();
		System.out.println(user.toString());
		UserInfo user2 = userDao.findByIdCard(user.getIdCard());
		if(user2==null) {
			userDao.addUser(user);
			rs.setStatus("0");
			rs.setMsg("新用户注册成功");
		}else {
			rs.setStatus("1");
			rs.setMsg("该用户已经存在");
		}
		return rs;
	}


	@Override
	public ResultMsg findByIdCard(String idCard) {
		ResultMsg rs = new ResultMsg();
		UserInfo user2 = userDao.findByIdCard(idCard);
		if(user2!=null) {
			rs.setStatus("0");
			rs.setMsg("查询成功");
		}else {
			rs.setStatus("1");
			rs.setMsg("查询失败");
		}
		return rs;
	}

}
