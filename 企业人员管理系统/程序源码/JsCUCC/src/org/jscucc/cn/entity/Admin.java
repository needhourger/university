package org.jscucc.cn.entity;

import java.io.Serializable;

public class Admin implements Serializable {

	private int id;
	private String account;
	private String password;
	private String leve;
	private String phone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLeve() {
		return leve;
	}

	public void setLeve(String leve) {
		this.leve = leve;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", account=" + account + ", password=" + password + ", leve=" + leve + ", phone="
				+ phone + "]";
	}

}
