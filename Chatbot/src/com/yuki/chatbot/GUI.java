package com.yuki.chatbot;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//设置百度API
	public static final String APP_ID="10516591";
	public static final String API_KEY="GRGH0HfpUjwvE1tnZ0HdEtyb";
	public static final String SECRET_KEY="1e4d107c7a621594a2601d1635fcbd25";
	private static BaiduAPI baidu = null;
	
	private JButton button_recoad=null;
	private JPanel panel=null;
	
	//定义录音格式
	private static final AudioFormat af =new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,16000f, 16, 1, 2, 16000f,true);
	//定义目标数据行,可以从中读取音频数据,该 TargetDataLine 接口提供从目标数据行的缓冲区读取所捕获数据的方法。
	private static TargetDataLine td = null;
	//定义源数据行,源数据行是可以写入数据的数据行。它充当其混频器的源。应用程序将音频字节写入源数据行，这样可处理字节缓冲并将它们传递给混频器。
	private static SourceDataLine sd = null;
	//定义字节数组输入输出流
	private ByteArrayInputStream bais = null;
	private ByteArrayOutputStream baos = null;
	//定义音频输入流
	private static AudioInputStream ais = null;
	//定义停止录音的标志，来控制录音线程的运行
	Boolean stopflag = false;
	private  URL url0=getClass().getResource("/micro0.png");
	private  URL url1=getClass().getResource("/micro1.png");
	private  Icon micro0 = new ImageIcon(url0);
	private  Icon micro1 = new ImageIcon(url1);

	
	public GUI(String title) throws Exception{
		super("Chat Robot");
		this.setSize(512, 512);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout(10, 10));
		
		panel = new JPanel();
		this.add(panel);
		panel.setLayout(new BorderLayout(10,10));
		
		button_recoad = new JButton("");
		button_recoad.setSize(256, 256);
		button_recoad.setIcon(micro0);
		//button_recoad.setBounds(100, 50, 100, 50);
		button_recoad.addMouseListener(this);
		panel.add(button_recoad,BorderLayout.CENTER);
		
		this.setVisible(true);
		
		baidu=new BaiduAPI(APP_ID, API_KEY, SECRET_KEY);
	}
	
	public void mouseClicked(MouseEvent e) {
//		System.out.println("clicked");
	}
	
	public void mousePressed(MouseEvent e) {
		if (e.getSource().equals(button_recoad)) {
//			System.out.println("pressed");
			button_recoad.setIcon(micro1);;
			capture();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if (e.getSource().equals(button_recoad)) {
//			System.out.println("pressed");
			stopflag=true;
			button_recoad.setIcon(micro0);
			save();
			baidu.voice2words();
			
		}
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}

	public void capture() {
		try {
			try {
				td.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, af);
			td = (TargetDataLine)(AudioSystem.getLine(info));
			
			td.open(af);
			
			td.start();
			
			Record record = new Record();
			Thread t1 = new Thread(record);
			t1.start();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return;
		}
	}

	
	public void save() {
		 
        byte audioData[] = baos.toByteArray();
        bais = new ByteArrayInputStream(audioData);
        ais = new AudioInputStream(bais,af, audioData.length / af.getFrameSize());
        //定义最终保存的文件名
        File file = null;
        //写入文件
        try {	
        	//以当前的时间命名录音的名字
        	//将录音的文件存放到F盘下语音文件夹下
        	File filePath = new File("./temp");
        	if(!filePath.exists())
        	{//如果文件不存在，则创建该目录
        		filePath.mkdir();
        	}
        	file = new File(filePath.getPath()+"/record.mp3");      
            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, file);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	//关闭流
        	try {
        		
        		if(bais != null)
        		{
        			bais.close();
        		} 
        		if(ais != null)
        		{
        			ais.close();		
        		}
			} catch (Exception e) {
				e.printStackTrace();
			}   	
        }

	}

	
	class Record implements Runnable{
		//定义存放录音的字节数组,作为缓冲区
		byte bts[] = new byte[10000];
		//将字节数组包装到流里，最终存入到baos中
		//重写run函数
		public void run() {	
			baos = new ByteArrayOutputStream();		
			try {
				System.out.println("Recoding...");
				stopflag = false;
				while(stopflag != true)
				{
					//当停止录音没按下时，该线程一直执行	
					//从数据行的输入缓冲区读取音频数据。
					//要读取bts.length长度的字节,cnt 是实际读取的字节数
					int cnt = td.read(bts, 0, bts.length);
					if(cnt > 0)
					{
						baos.write(bts, 0, cnt);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					//关闭打开的字节数组流
					if(baos != null)
					{
						baos.close();
					}	
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					td.drain();
					td.close();
				}
			}
		}
	}
	
}