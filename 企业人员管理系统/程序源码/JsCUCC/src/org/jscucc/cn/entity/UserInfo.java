package org.jscucc.cn.entity;

import java.io.Serializable;

public class UserInfo implements Serializable{
	
	private int id;
	private String name;
	private String age;
	private String sex;
	private String idCard;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
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
		return "UserInfo [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", idCard=" + idCard
				+ ", phone=" + phone + ", address=" + address + "]";
	}
	
	
}
