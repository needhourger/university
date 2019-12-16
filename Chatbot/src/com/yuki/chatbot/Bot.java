package com.yuki.chatbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import javazoom.jl.player.advanced.jlap;

class Bot{
	private static final String URL="http://openapi.tuling123.com/openapi/api/v2";
	private static final String API_KEY="2cf380aace884edcaef89f130a1e08ec";
	private static final String USER_ID="114227";
	
	public String replay(String words) {
		JSONObject json = new JSONObject();
		json.put("reqType", 0);
		
		JSONObject perception_json = new JSONObject();
		JSONObject inputText_json = new JSONObject();
		inputText_json.put("text", words);
		perception_json.put("inputText", inputText_json);
		json.put("perception",perception_json);
		
		JSONObject userInfo_json = new JSONObject();
		userInfo_json.put("apiKey", API_KEY);
		userInfo_json.put("userId", USER_ID);
		json.put("userInfo", userInfo_json);
		System.out.println(json.toString());
		System.out.println("");
		
		JSONObject replay = JsonSMS(json.toString(), URL);
		JSONArray results = replay.getJSONArray("results");

		String ret = "";
		for (int i=0;i<results.length();i++) {
			JSONObject volume = (JSONObject) results.get(i);
			if (volume.getString("resultType").compareTo("text")==0) {
				JSONObject temp =  volume.getJSONObject("values");
				
				System.out.println(results.toString());
				ret=ret+temp.get("text").toString();
			};
			if (volume.getString("resultType").compareTo("url")==0) {
				JSONObject temp = volume.getJSONObject("values");
				String url = temp.getString("url");
				System.out.println(url);
				try {
					URI uri=new URI(url);
					java.awt.Desktop.getDesktop().browse(uri);
				} catch ( Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}
		}
		System.out.println(replay.toString());
		
		return ret;
	}
	
	private static JSONObject JsonSMS(String postData, String postUrl) {
	    try {
	        //发送POST请求
	        URL url = new URL(postUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        //conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Connection", "close");
	        conn.setUseCaches(false);
	        conn.setDoOutput(true);
	        conn.setRequestProperty("Content-Length", "" + postData.length());
	        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
	        out.write(postData);
	        out.flush();
	        out.close();
	        //获取响应状态
	        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
	            System.out.println("connect failed!");
	            return null;
	        }
	        //获取响应内容体
	        
	        String line, result = "";
	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
	        while ((line = in.readLine()) != null) {
	            result += line + "\n";
	        }
	        in.close();
	        JSONObject json = new JSONObject(result);
	        System.out.println(postData);
	        return json;
	    } catch (IOException e) {
	        e.printStackTrace(System.out);
	    }
	    return null;
	}

}
