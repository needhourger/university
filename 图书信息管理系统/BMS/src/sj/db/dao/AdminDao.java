package sj.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sj.db.bean.Administrator;
import sj.db.bean.Record;
import sj.db.util.GenerateCode;
import sj.db.util.JDBCUtils;

public class AdminDao {
	//登陆判断
	public boolean judegLogin(Administrator admin) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select Admin_name,Admin_pwd from Administrator where Admin_name ='"
					+ admin.getAdmin_name()
					+ "'and Admin_pwd ='"
					+ admin.getAdmin_pwd() 
					+ "'";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return false;
	}
	//获取用户列表
	public List<Administrator> getAdmin(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Administrator>adminList = new ArrayList<Administrator>();
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				String sql = "select * from Administrator";
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					Administrator admin = new Administrator();
					admin.setAdmin_id(rs.getInt("Admin_id"));
					admin.setAdmin_name(rs.getString("Admin_name"));
					admin.setAdmin_pwd(rs.getString("Admin_pwd"));
					admin.setAdmin_new_code(rs.getString("Admin_code"));
					adminList.add(admin);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.release(rs, stmt, conn);
			}
			return null;
	}
	//根据id获取用户
	//增加用户
	public boolean insert(Administrator admin) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs =null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			//查询该用户所用邀请码是否在数据库中
			String old_code = admin.getAdmin_old_code();
			String sql1 = "select Admin_code from Administrator "
					+ "where Admin_code ='"+ old_code+"'";
			rs = stmt.executeQuery(sql1);
			if(rs.next()) {//邀请码存在
				//随机分配一个邀请码给新注册的账号
				int code = GenerateCode.code();
				String sql2 = "insert into Administrator(Admin_id,Admin_name,Admin_pwd,Admin_code)"
						+ "values(" + null 
						+ ",'" 
						+ admin.getAdmin_name() 
						+ "','" 
						+ admin.getAdmin_pwd() 
						+ "','" 
						+ code 
						+ "')"; 
				int num = stmt.executeUpdate(sql2);
				if(num > 0) {
					return true;//注册成功
				}
				return false;//注册失败
			} else {//邀请码不存在
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return false;
	}
	//删除用户
	//修改用户
	public boolean update(Administrator admin) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs =null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "update administrator set Admin_id='"+ null +"',"
					+ "Admin_name='"+ admin.getAdmin_name() +"',"
							+ "Admin_pwd='"+ admin.getAdmin_pwd();//邀请码不可修改
			int num = stmt.executeUpdate(sql);
			if(num > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return false;
	}
}
