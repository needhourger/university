package cc.yuukisama.Entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import cc.yuukisama.JFrame.GameJFrame;

public class Rocket extends UnityBase {
	
	public Rocket() {
		// TODO 自动生成的构造函数存根
		WIDTH=(int)(GameJFrame.WIDTH*0.1);
		HEIGHT=(int)(GameJFrame.HEIGHT*0.08);
		x=(int)(GameJFrame.WIDTH+random.nextInt(GameJFrame.WIDTH*2));
		y=(int)(GameJFrame.HEIGHT*0.5-random.nextInt(GameJFrame.HEIGHT/2));
		imageNum=1;
		index=0;
		
		image=new Image[imageNum];
		for (int i=0;i<imageNum;i++) 
			image[i]=new ImageIcon("resource/image/daodan.png").getImage();
	}
	 
	 public void paint(Graphics g) {
		 g.drawImage(image[index], x, y, WIDTH, HEIGHT, null);
		 x-=10;
	 }
}
