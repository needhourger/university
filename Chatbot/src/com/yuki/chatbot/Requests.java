package com.yuki.chatbot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//网络数据传输数据实现
class Requests{
	private final String USER_AGENT="Mozilla/5.0";
	private String url="";
	
	public Requests(String x) {
		url=x;
	}
	
	public String Get() throws Exception{
		URL obj=new URL(url);
		HttpURLConnection connection=(HttpURLConnection) obj.openConnection();
		
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		
		int responseCode=connection.getResponseCode();
		System.out.println(responseCode);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputline;
		StringBuffer response = new StringBuffer();
		
		while ((inputline=in.readLine())!=null) {
			response.append(inputline);
		}
		in.close();
		return response.toString();
		
	}
	
	public String Post(String parm) throws Exception{
		URL obj=new URL(url);
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		
		connection.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(parm);
		wr.flush();
		wr.close();
		
		int responseCode = connection.getResponseCode();
		System.out.println(responseCode);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputline;
		StringBuffer response = new StringBuffer();
		
		while((inputline = in.readLine())!=null) {
			response.append(inputline);
		}
		in.close();
		
		return response.toString();
		
	}
}

