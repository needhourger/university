package com.four.bird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 *
 */
public class Ground {
	BufferedImage image;
	
	int x;
	int y;
	int width;
	int height;
	public Ground() throws IOException {
		y = 500;
		image = ImageIO.read(getClass().getResource("ground.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 0;
	}
	
	public void step(){
		x--;
		if(x<= -(111)){
			x=0;
		}
	}

	public void paint(Graphics g) {
		g.drawImage(image, x, y, null);		
	}
}
