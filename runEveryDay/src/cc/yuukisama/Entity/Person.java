package cc.yuukisama.Entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import cc.yuukisama.JFrame.GameJFrame;

public class Person extends UnityBase{
	
	public Person() {
		// TODO 自动生成的方法存根
		WIDTH=(int)(GameJFrame.WIDTH*0.1);
		HEIGHT=(int)(GameJFrame.HEIGHT*0.15);
		x=(int)(GameJFrame.WIDTH*0.2);
		y=(int)(GameJFrame.HEIGHT*0.63);
		imageNum=9;
		index=0;
		
		time=0;
		interval=15;

		image=new Image[imageNum];
		for (int i=0;i<imageNum;i++) 
			image[i]=new ImageIcon("resource/image/"+(i+1)+".png").getImage();
	}
	
	public void paint(Graphics g) {
		if (isGo()) {
			step();
			g.drawImage(image[index], x, y, WIDTH, HEIGHT, null);
		}
		else{
			g.drawImage(image[index], x, y, WIDTH, HEIGHT, null);
		}
	}

	public void jump() {
		// TODO 自动生成的方法存根
		int pre=this.y;
		this.y-=GameJFrame.HEIGHT/3;
	}
	
	

}
