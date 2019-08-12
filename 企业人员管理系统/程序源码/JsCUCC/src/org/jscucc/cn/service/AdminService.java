package org.jscucc.cn.service;

import org.jscucc.cn.entity.Admin;
import org.jscucc.cn.entity.EmpInfo;
import org.jscucc.cn.entity.ResultMsg;

public interface AdminService {
	
	public ResultMsg checkLogin(String account,String password);

	public ResultMsg findByIdAdmin(int id);
	
	public ResultMsg findAdminAll();
	
	public ResultMsg findEmpInfo(String account);

	public ResultMsg updatePwd(String account,String newpwd,String oldpwd);

	public ResultMsg updateEmpInfo(EmpInfo empInfo);

	public ResultMsg addAdmin(Admin admin);

	public ResultMsg updateAdmin(Admin admin);

	public ResultMsg deleteAdmin(String account);
}
