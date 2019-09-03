package cc.yuukisama.Entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import cc.yuukisama.JFrame.GameJFrame;

public class Pillar extends UnityBase {
	public Pillar() {
		// TODO 自动生成的构造函数存根
		WIDTH=(int)(GameJFrame.WIDTH*0.08);
		HEIGHT=(int)(GameJFrame.HEIGHT*0.35+random.nextInt(GameJFrame.HEIGHT/5));
		x=(int)(GameJFrame.WIDTH+random.nextInt(GameJFrame.WIDTH));
		y=0;
		imageNum=1;
		index=0;
		
		image=new Image[imageNum];
		for (int i=0;i<imageNum;i++) {
			image[i]=new ImageIcon("resource/image/1"+(random.nextInt(4)+1)+".png").getImage();
		}
	}
	
	public Pillar(Pillar pre) {
		// TODO 自动生成的构造函数存根
		WIDTH=(int)(GameJFrame.WIDTH*0.08);
		HEIGHT=(int)(GameJFrame.HEIGHT*0.35+random.nextInt(GameJFrame.HEIGHT/5));
		x=(int)(pre.x+GameJFrame.WIDTH/2+random.nextInt(GameJFrame.WIDTH/3));
		y=0;
		imageNum=1;
		index=0;
		
		image=new Image[imageNum];
		for (int i=0;i<imageNum;i++) 
			image[i]=new ImageIcon("resource/image/1"+(random.nextInt(4)+1)+".png").getImage();
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(image[index], x--, y, WIDTH, HEIGHT, null);
	}
}
