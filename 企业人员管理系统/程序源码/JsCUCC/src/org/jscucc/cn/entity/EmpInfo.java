package org.jscucc.cn.entity;

import java.io.Serializable;

public class EmpInfo implements Serializable{
	
	private int id;
	private String name;
	private String idCard;
	private String age;
	private String account;
	private String phone;
	private String address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "AdminInfo [id=" + id + ", name=" + name + ", idCard=" + idCard + ", age=" + age + ", account=" + account
				+ ", phone=" + phone + ", address=" + address + "]";
	}
	
	

}
