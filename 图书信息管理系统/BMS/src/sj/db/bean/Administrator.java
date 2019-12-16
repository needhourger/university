package sj.db.bean;

public class Administrator {
	private int Admin_id;
	private String Admin_name;
	private String Admin_pwd;
	private String Admin_new_code;
	private String Admin_old_code;
	
	//管理员ID
	public int getAdmin_id() {
		return Admin_id;
	}
	public void setAdmin_id(int admin_id) {
		Admin_id = admin_id;
	}
	//管理员账户
	public String getAdmin_name() {
		return Admin_name;
	}
	public void setAdmin_name(String admin_name) {
		Admin_name = admin_name;
	}
	//管理员密码
	public String getAdmin_pwd() {
		return Admin_pwd;
	}
	public void setAdmin_pwd(String admin_pwd) {
		Admin_pwd = admin_pwd;
	}
	//管理员注册账号后随即获得的邀请码
	public String getAdmin_new_code() {
		return Admin_new_code;
	}
	public void setAdmin_new_code(String admin_code) {
		Admin_new_code = admin_code;
	}
	//管理员在注册账号时使用的邀请码
	public String getAdmin_old_code() {
		return Admin_old_code;
	}
	public void setAdmin_old_code(String admin_old_code) {
		Admin_old_code = admin_old_code;
	}
}
