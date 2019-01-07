package com.yuki.chatbot;

import java.io.File;

public class Chatbot{	
	//Ö÷º¯Êý
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		File temp = new File("./temp");
		if (!temp.exists()) temp.mkdir();
		System.out.println("halloworld");	
		GUI gui=new GUI("Chat Robot");
	}
	
	
}

