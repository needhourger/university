package cc.yuukisama.Entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;


public abstract class UnityBase {
	public int x=0;
	public int y=0;
	public int WIDTH=0;
	public int	HEIGHT=0;
	
	
	public Image[] image=null;
	public int index=0;
	public int imageNum=1;
	
	public int interval=0;
	public int time=0;
	
	public static Random random=new Random();
	abstract public void paint(Graphics g);
	
	public void step() {
		if (index==imageNum-1) index=0;
		else index+=1;
	}
	
	public boolean isGo() {
		if (time%interval==0) {
			time=time%interval+1;
			return true;
		}
		time++;
		return false;
	}
}
