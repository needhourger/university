package org.jscucc.cn.controller;

import javax.annotation.Resource;

import org.jscucc.cn.entity.Admin;
import org.jscucc.cn.entity.EmpInfo;
import org.jscucc.cn.entity.ResultMsg;
import org.jscucc.cn.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AdminController {
	
	@Resource
	AdminService adminService;
	
	//根据管理员id，查询管理员
	@RequestMapping("/findByIdAdmin.do")
	@ResponseBody
	public ResultMsg findByIdAdmin(int id) {
		ResultMsg  rsm = adminService.findByIdAdmin(id);
		return rsm;
	}
	
	
	//查询所有管理员账号
	@RequestMapping("/findAdminRole.do")
	@ResponseBody
	public ResultMsg findAdminAll() {
		ResultMsg rs = adminService.findAdminAll();
		return rs;
	}
	
	@RequestMapping("/findEmpInfo.do")
	@ResponseBody
	public ResultMsg findEmpInfo(String account){
		ResultMsg rs = adminService.findEmpInfo(account);
		return rs;
	}
	
	@RequestMapping("/updatepwd.do")
	@ResponseBody
	public ResultMsg updatePwd(String account,String newpwd,String oldpwd) {
		ResultMsg rs = adminService.updatePwd(account, newpwd,oldpwd);
		return rs;
	}
	
	@RequestMapping("/updateEmpInfo.do")
	@ResponseBody
	public ResultMsg updateEmpInfo(EmpInfo admininfo) {
		System.out.println(admininfo.toString());
		
		ResultMsg rs = adminService.updateEmpInfo(admininfo);
		return rs;
	}
	
	@RequestMapping("/addAdmin.do")
	@ResponseBody
	public ResultMsg addAdmin(Admin admin) {
		ResultMsg rs = adminService.addAdmin(admin);
		return rs;
	}
	
	@RequestMapping("/updateAdmin.do")
	@ResponseBody
	public ResultMsg updateAdmin(Admin admin) {
		ResultMsg rs = adminService.updateAdmin(admin);
		return rs;
	}
	
	@RequestMapping("/delAdmin.do")
	@ResponseBody
	public ResultMsg deleteAdmin(String account) {
		ResultMsg rs = adminService.deleteAdmin(account);
		return rs;
	}
}
