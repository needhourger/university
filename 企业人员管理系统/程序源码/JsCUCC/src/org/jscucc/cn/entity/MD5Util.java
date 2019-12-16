package org.jscucc.cn.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.codec.binary.Base64;

//MD5 ,�������
public class MD5Util {
	
	public static void main(String[] args) {
		System.out.println(md5("123456"));
	}
	
	public static String md5(String str) {
		String pwd ="";
		try {
			MessageDigest md = 
					MessageDigest.getInstance("MD5");
			//MD5.����ֱ�Ӷ��ַ������ܣ�
			//������ת���ֽ����飬���ܼ���
			byte[] input = str.getBytes();
			//digest(input)��MD���ܷ���
			byte[] output = md.digest(input);
			//��outputת�� �ַ���������Base64�㷨
			pwd = Base64.encodeBase64String(output);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pwd;
	}
}
