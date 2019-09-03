package com.four.bird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
/**
 * 
 */
public class Column {
	BufferedImage image;
	//以柱子的中间作为柱子的位置
	int x;
	int y; // 140 ~ 280
	int width;
	int height;
	int gap = 144;
	Random r = new Random();
	
	int distance = 245;
	
	public Column(int n) throws IOException { 
		image = ImageIO.read(getClass().getResource("column.png"));
		width = image.getWidth();
		height = image.getHeight();
		this.x = distance * n + 80 + 432;
		this.y = r.nextInt(120) + 220;
	}
	
	public void step(){
		x--;
		if(x<=-width/2){
			x = distance*2-width/2;
			this.y = r.nextInt(120) + 220;
		}
	}
	public void paint(Graphics g){
		//g.drawRect(x-width/2, y-height/2, width, height/2-gap/2);
		//g.drawRect(x-width/2, y+gap/2, width, height/2-gap/2);
		g.drawImage(image, x-width/2, y-height/2, null);
	}
}
