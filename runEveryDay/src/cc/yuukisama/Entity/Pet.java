package cc.yuukisama.Entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import cc.yuukisama.JFrame.GameJFrame;

public class Pet extends UnityBase {
	public Pet() {
		// TODO 自动生成的构造函数存根
		WIDTH=(int)(GameJFrame.WIDTH*0.06);
		HEIGHT=(int)(GameJFrame.HEIGHT*0.1);
		x=(int)(GameJFrame.WIDTH*0.1);
		y=(int)(GameJFrame.HEIGHT*0.68);
		imageNum=6;
		index=0;
		
		time=0;
		interval=15;
		
		image=new Image[imageNum];
		for (int i=0;i<imageNum;i++) {
			image[i]=new ImageIcon("resource/image/d"+(i+1)+".png").getImage();
		}
	}
	
	public void paint(Graphics g) {
		if (isGo()) {
			step();
			g.drawImage(image[index], x, y, WIDTH, HEIGHT, null);
		}
		else {
			g.drawImage(image[index], x, y, WIDTH, HEIGHT, null);
		}
		
	}
}
