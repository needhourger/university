package com.yuki.chatbot;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;

import javazoom.jl.player.Player;



public class BaiduAPI extends AipSpeech{
	
	
	Bot bot=new Bot();
	
	private static int spd = 5;
	private static int pit = 5;
	private static int vol = 5;
	private static int per = 1;
	
	public void setspd(int x) {
		if (0<=x && x<=9) {
			spd=x;
		}
		else return;
	}
	
	public void setpit(int x) {
		if (0<=x && x<=9) {
			pit=x;
		}
		else return;
	}
	
	public void setvol(int x) {
		if (0<=x && x<=15) {
			vol=x;
		}
		else return;
	}
	
	public void setper(int x) {
		if (x==0 || x==1 || x==3 || x==4) {
			per=x;
		}
		else return;
	}
	
	
	
	public BaiduAPI(String appId, String apiKey, String secretKey) {
		super(appId, apiKey, secretKey);
		// TODO 自动生成的构造函数存根
	}
	
	
	//文字转语音
	public void words2voice(String words) throws Exception{
		HashMap<String, Object> options = new HashMap<String,Object>();
		
		options.put("spd",String.valueOf(spd));
		options.put("pit", String.valueOf(pit));
		options.put("per", String.valueOf(per));
		
		TtsResponse res = this.synthesis(words, "zh", 1, options);
		byte[] data = res.getData();
		JSONObject res1 = res.getResult();
		if (data!=null) {
			try {
				Util.writeBytesToFileSystem(data, "./temp/output.mp3");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (res1!=null) {
			System.out.println(res1.toString(2));
		}
	}
	
	public String voice2words() {
		byte[] data =loadvoice();
		JSONObject ret = this.asr(data, "pcm", 16000,null);
		File f = new File("./temp/record.mp3");
		if (f.exists()) f.delete();
		System.out.println(ret.toString());
		if (ret.has("result")) {
			JSONArray temp=ret.getJSONArray("result");
			String words = temp.get(0).toString();
			System.out.println(words);
			
			if (words.indexOf("切换萝莉")!=-1) this.setper(4);
			if (words.indexOf("切换默认")!=-1) this.setper(1);
			if (words.indexOf("切换御姐")!=-1) this.setper(0);
			if (words.indexOf("切换男人")!=-1) this.setper(1);
			if (words.indexOf("切换其他")!=-1) this.setper(3);
			if (words.indexOf("打开网页百度")!=-1) {
				URI uri;
				try {
					uri = new URI("https://www.baidu.com/");
					java.awt.Desktop.getDesktop().browse(uri);
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}
			if (words.indexOf("打开浏览器")!=-1) {
				URI uri;
				try {
					uri = new URI("http://www.baidu.com/");
					java.awt.Desktop.getDesktop().browse(uri);
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}
			
			try {
				this.words2voice(bot.replay(words));
				this.voiceplay("./temp/output.mp3");
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return words;
		}
	
		return null;
		
	}
	
	private void voiceplay(String filePath) {
		try {
			BufferedInputStream buff = new BufferedInputStream(new FileInputStream(filePath));
			Player player = new Player(buff);
			player.play();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			File file =new File(filePath);
			if (file.exists()) file.delete();
		}
		
	}

	private byte[] loadvoice() {
		ByteArrayOutputStream out=null;
		try {
			FileInputStream in = new FileInputStream("./temp/record.mp3");
			out = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int i=0;
			while ((i=in.read(b))!=-1) {
				out.write(b,0,b.length);
			}
			out.close();
			in.close();
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		byte[] ret=out.toByteArray();
		return ret;
	}
	
}
