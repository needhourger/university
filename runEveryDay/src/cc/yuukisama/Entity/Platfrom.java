package cc.yuukisama.Entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import cc.yuukisama.JFrame.GameJFrame;

public class Platfrom extends UnityBase {

	public Platfrom() {
		// TODO 自动生成的构造函数存根
		WIDTH=(int)(GameJFrame.WIDTH*0.1+random.nextInt(GameJFrame.WIDTH/10));
		HEIGHT=(int)(GameJFrame.HEIGHT*0.05);
		x=(int)(GameJFrame.WIDTH+random.nextInt(GameJFrame.WIDTH/2));
		y=(int)(GameJFrame.HEIGHT/4+random.nextInt(GameJFrame.HEIGHT/5));
		imageNum=1;
		index=0;
		
		time=0;
		interval=1000;
		
		image=new Image[imageNum];
		for (int i=0;i<imageNum;i++) 
			image[i]=new ImageIcon("resource/image/hhh.png").getImage();
	}
	
	public Platfrom(Platfrom pre) {
		WIDTH=(int)(GameJFrame.WIDTH*0.1+random.nextInt(GameJFrame.WIDTH/10));
		HEIGHT=(int)(GameJFrame.HEIGHT*0.05);
		x=(int)(pre.x+GameJFrame.WIDTH/3+random.nextInt(GameJFrame.WIDTH/5));
		y=(int)(GameJFrame.HEIGHT/4+random.nextInt(GameJFrame.HEIGHT/5));
		imageNum=2;
		index=0;
		
		time=0;
		interval=1000;
		
		image=new Image[imageNum];
		for (int i=0;i<imageNum;i++) {
			image[i]=new ImageIcon("resource/image/hhh.png").getImage();
		}
	}
	
	
	@Override
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
