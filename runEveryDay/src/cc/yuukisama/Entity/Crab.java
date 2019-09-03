package cc.yuukisama.Entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import cc.yuukisama.JFrame.GameJFrame;

public class Crab extends UnityBase {	
	
	public Crab() {
		// TODO 自动生成的构造函数存根
		WIDTH=(int)(GameJFrame.WIDTH*0.1);
		HEIGHT=(int)(GameJFrame.HEIGHT*0.15);
		x=(int)(GameJFrame.WIDTH+random.nextInt(GameJFrame.WIDTH/3));
		y=(int)(GameJFrame.HEIGHT*0.64);
		imageNum=2;
		index=0;
		
		time=0;
		interval=50;
		
		image=new Image[imageNum];
		for (int i=0;i<imageNum;i++) 
			image[i]=new ImageIcon("resource/image/a"+(i+1)+".png").getImage();
		
	}
	
	public Crab(Crab pre) {
		// TODO 自动生成的构造函数存根
		WIDTH=(int)(GameJFrame.WIDTH*0.1);
		HEIGHT=(int)(GameJFrame.HEIGHT*0.15);
		x=(int)(pre.x+GameJFrame.WIDTH/2+random.nextInt(GameJFrame.WIDTH/3));
		y=(int)(GameJFrame.HEIGHT*0.64);
		imageNum=2;
		index=0;
		
		time=0;
		interval=50;
		
		image=new Image[imageNum];
		for (int i=0;i<imageNum;i++) {
			image[i]=new ImageIcon("resource/image/a"+(i+1)+".png").getImage();
		}
	}
	
	public void paint(Graphics g) {
		// TODO 自动生成的方法存根
		if (isGo()) {
			step();
			g.drawImage(image[index], x--, y, WIDTH, HEIGHT, null);
		}
		else {
			g.drawImage(image[index], x--, y, WIDTH, HEIGHT, null);
		}
		
	}
}
