package org.jscucc.cn.dao;

import java.util.List;
import java.util.Map;

import org.jscucc.cn.entity.Admin;
import org.jscucc.cn.entity.EmpInfo;

public interface AdminDao {
	
	public Admin checkLogin(String account,String password);

	//根据ID查询管理员
	public Admin findByIdAdmin(int id);
	
	//查询所有管理员账号 
	public List<Admin> findAdminAll();
	
	//查看员工个人信息
	public EmpInfo findEmpInfo(String account);

	//管理员密码修改
	public void updatePwd(String password,String account);

	//员工个人信息修改
	public void updateEmpInfo(EmpInfo empInfo);
	
	//根据手机号查询，员工个人信息
	public EmpInfo findByPhone(String phone);
	
	//根据Account，查询管理员账号
	public Admin findByAccount(String account);

	//添加新管理员
	public void addAdmin(Admin admin);
	
	//更新Empinfo表中的 account字段
	public void updataAccount(Admin admin);
	
	//修改管理员账号信息
	public void updateAdmin(Admin admin);
	
	//修改empinfo表中的 account
	public void updateEmpInfoAccount(Map map);
	
	//删除管理员账号
	public void deleteAdmin(String account);
}
