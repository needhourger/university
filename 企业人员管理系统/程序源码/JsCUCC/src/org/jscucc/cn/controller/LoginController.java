package org.jscucc.cn.controller;

import javax.annotation.Resource;

import org.jscucc.cn.entity.ResultMsg;
import org.jscucc.cn.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	@Resource
	AdminService adminService;
	
	//跳转到登陆页面
	@RequestMapping("/tologin.do")
	public String tolog() {
		return "login";
	}
	
	
	//登陆验证
	@RequestMapping("/checklogin.do")
	@ResponseBody
	public ResultMsg checkLogin(String account,String pwd) {
		ResultMsg rm = adminService.checkLogin(account, pwd);
		return rm;
	}
	

}
