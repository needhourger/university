package org.jscucc.cn.controller;

import javax.annotation.Resource;

import org.jscucc.cn.entity.ResultMsg;
import org.jscucc.cn.entity.UserInfo;
import org.jscucc.cn.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	
	@Resource
	private UserService userService;
	
	
	
	@RequestMapping("/adduser.do")
	@ResponseBody
	public ResultMsg addUser(UserInfo user) {
		ResultMsg rs = userService.addUser(user);
		return rs;
	}
	

	@RequestMapping("/queryUser.do")
	@ResponseBody
	public ResultMsg findByIdCard(String idCard) {
		ResultMsg rs = userService.findByIdCard(idCard);
		return rs;
	}
}
