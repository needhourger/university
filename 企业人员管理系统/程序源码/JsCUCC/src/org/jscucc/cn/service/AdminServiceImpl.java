package org.jscucc.cn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jscucc.cn.dao.AdminDao;
import org.jscucc.cn.entity.Admin;
import org.jscucc.cn.entity.EmpInfo;
import org.jscucc.cn.entity.MD5Util;
import org.jscucc.cn.entity.ResultMsg;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Resource
	public AdminDao adminDao;
	
	@Override
	public ResultMsg checkLogin(String account, 
								String password) {
		password = MD5Util.md5(password);
		Admin admin=adminDao.checkLogin(account, password);
		ResultMsg rm = new ResultMsg();
		if(admin!=null) {
			rm.setStatus("0");
			rm.setData(admin);
		}else {
			rm.setStatus("1");
			rm.setMsg("用户名或者密码错误");
		}
		return rm;
	}

	
	
	//根据id查询管理员
	@Override
	public ResultMsg findByIdAdmin(int id) {
		ResultMsg result = new ResultMsg();
		Admin admin = adminDao.findByIdAdmin(id);
		result.setStatus("0");
		result.setData(admin);	
		return result;
	}



	@Override
	public ResultMsg findAdminAll() {
		List<Admin> adminList=adminDao.findAdminAll();
		ResultMsg rs = new ResultMsg();
		rs.setStatus("0");
		rs.setData(adminList);
		return rs;
	}



	@Override
	public ResultMsg findEmpInfo(String account) {
		EmpInfo  adf = adminDao.findEmpInfo(account);
		ResultMsg rs = new ResultMsg();
		rs.setStatus("0");
		rs.setData(adf);
		return rs;
	}



	@Override
	public ResultMsg updatePwd(String account,
				String newpwd,String oldpwd) {
		ResultMsg rs = new ResultMsg();
		oldpwd = MD5Util.md5(oldpwd);
		Admin admin = adminDao.checkLogin(account, oldpwd);
		if(admin!=null) {
			//将明文密码，通过MD5加密，转成密文
			newpwd = MD5Util.md5(newpwd);
			adminDao.updatePwd(newpwd, account);
			rs.setStatus("0");
			rs.setMsg("修改成功,使用新密码重新登陆！");
		}else {
			rs.setStatus("1");
			rs.setMsg("输入的旧密码有错误！");
		}
		return rs;
	}



	@Override
	public ResultMsg updateEmpInfo(EmpInfo empInfo) {
		adminDao.updateEmpInfo(empInfo);
		ResultMsg rs = new ResultMsg();
		rs.setStatus("0");
		rs.setMsg("个人信息修改成功");
		return rs;
	}


	//添加管理员
	@Override
	public ResultMsg addAdmin(Admin admin) {
		ResultMsg rs = new ResultMsg();
		//判断EmpInfo表中是否有手机号
		EmpInfo empInfo = adminDao.findByPhone(admin.getPhone());
		//System.out.println(adminInfo.toString());
		if(empInfo==null) {
			rs.setStatus("1");
			rs.setMsg("该手机号，没有对应的管理员信息");
		}else if(empInfo.getAccount()!=null){
			//管理员admininfo表中，
			//Account字段是否已经有账号
			rs.setStatus("1");
			rs.setMsg("该手机号管理员，已经有管理员账号");
		}else {
			//判断账号是否已被占用
			Admin admin2=adminDao.findByAccount(admin.getAccount());
			if(admin2!=null) {
				rs.setStatus("1");
				rs.setMsg("该账号，已经被占用");
			}else {
				String pwd = MD5Util.md5(admin.getPassword());
				admin.setPassword(pwd);
				adminDao.addAdmin(admin);
				rs.setStatus("0");
				rs.setMsg("注册成功!!");
				adminDao.updataAccount(admin);
			}
		}
		return rs;
	}



	@Override
	public ResultMsg updateAdmin(Admin admin) {
		ResultMsg rs = new ResultMsg();
		Admin admin2 = adminDao.findByIdAdmin(admin.getId());
		Admin admin3 = adminDao.findByAccount(admin.getAccount());
		if(admin3!=null) {
			rs.setStatus("1");
			rs.setMsg("输入的账号已经被占用");
		}else {
			adminDao.updateAdmin(admin);
			rs.setStatus("0");
			rs.setMsg("修改成功");
			Map<String,String> map = new HashMap<>();
			map.put("newaccount", admin.getAccount());
			map.put("oldaccount", admin2.getAccount());
			adminDao.updateEmpInfoAccount(map);
		}
		
		return rs;
	}


	//删除管理员账号
	@Override
	public ResultMsg deleteAdmin(String account) {
		ResultMsg rs = new ResultMsg();
		adminDao.deleteAdmin(account);
		rs.setStatus("0");
		rs.setMsg("删除成功");
		Map<String,String> map = new HashMap<>();
		map.put("newaccount", "");
		map.put("oldaccount", account);
		adminDao.updateEmpInfoAccount(map);
		return rs;
	}
	
	
	

}
