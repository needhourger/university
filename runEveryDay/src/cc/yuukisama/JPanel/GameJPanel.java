package cc.yuukisama.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cc.yuukisama.Entity.Coin;
import cc.yuukisama.Entity.Crab;
import cc.yuukisama.Entity.GoldCoin;
import cc.yuukisama.Entity.Person;
import cc.yuukisama.Entity.Pet;
import cc.yuukisama.Entity.Pillar;
import cc.yuukisama.Entity.Platfrom;
import cc.yuukisama.Entity.Rocket;
import cc.yuukisama.JFrame.GameJFrame;

public class GameJPanel extends JPanel implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5277870295992268831L;
	
	public static int score=0;
	public static int distance=0;
	
	private static Image background=null;
	private static Image showfield=null;
	private static Person person=null;
	private static Pet pet=null;
	private static Crab[] crabs=null;
	private static Rocket[] rockets=null;
	private static Pillar[] pillars=null;
	private static Platfrom[] platfroms=null;
	private static Coin[] coins=null;
	private static GoldCoin[] goldCoins=null;
	private static final int crabNum=2;
	private static final int rockNum=2;
	private static final int pillarNum=4;
	private static final int platfromNum=3;
	private static final int coinNum=50;
	private static final int goldNum=50;
	
	private static int a=0;
	@SuppressWarnings("unused")
	private static Random random=new Random();
	public GameJPanel() {
		// TODO 自动生成的构造函数存根
		background=new ImageIcon("resource/image/cc.png").getImage();
		showfield=new ImageIcon("resource/image/a12.png").getImage();	
		person=new Person();
		pet=new Pet();
		
		crabs=new Crab[crabNum];
		for (int i=0;i<crabNum;i++)
			if (i==0) crabs[i]=new Crab();
			else crabs[i]=new Crab(crabs[i-1]);
		
		pillars=new Pillar[pillarNum];
	    for (int i=0;i<pillarNum;i++)
	    	if (i==0) pillars[i]=new Pillar();
	    	else pillars[i]=new Pillar(pillars[i-1]);
	    
		rockets=new Rocket[rockNum];
		for (int i=0;i<rockNum;i++) rockets[i]=new Rocket();
		
		platfroms=new Platfrom[platfromNum];
		for (int i=0;i<platfromNum;i++)
			if (i==0) platfroms[i]=new Platfrom();
			else platfroms[i]=new Platfrom(platfroms[i-1]);
		
		coins=new Coin[coinNum];
		for (int i=0;i<coinNum;i++)
			if (i==0) coins[i]=new Coin((int)(GameJFrame.HEIGHT*0.7), 4);
			else coins[i]=new Coin(coins[i-1]);
		
		goldCoins=new GoldCoin[goldNum];
		for (int i=0;i<goldNum;i++)
			goldCoins[i]=new GoldCoin(random.nextInt(2)+2);
		
		this.addKeyListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO 自动生成的方法存根
		a--;
		g.drawImage(background, a, 0, GameJFrame.WIDTH, GameJFrame.HEIGHT, null);
		g.drawImage(background, GameJFrame.WIDTH+a, 0, GameJFrame.WIDTH, GameJFrame.HEIGHT, null);
		if (a==-GameJFrame.WIDTH) a=0;
		
		
		
		for (int i=0;i<coinNum;i++) {
			coins[i].paint(g);
			if (coins[i].x<-coins[i].WIDTH) coins[i]=new Coin(coins[(i+coinNum-1)%coinNum]);
		}
		
		person.paint(g);
		pet.paint(g);
		
		for (int i=0;i<crabs.length;i++) {
			crabs[i].paint(g);
			if (crabs[i].x<-crabs[i].WIDTH) crabs[i]=new Crab(crabs[(i+crabNum-1)%crabNum]);
		}
			
		for (int i=0;i<pillars.length;i++) {
			pillars[i].paint(g);
			if (pillars[i].x<-pillars[i].WIDTH) pillars[i]=new Pillar(pillars[(i+pillarNum-1)%pillarNum]);
		}
			
		for (int i=0;i<rockets.length;i++) {
			rockets[i].paint(g);
			if (rockets[i].x<-rockets[i].WIDTH) rockets[i]=new Rocket();
		}
		
		for (int i=0;i<platfromNum;i++) {
			platfroms[i].paint(g);
			if (platfroms[i].x<-platfroms[i].WIDTH) platfroms[i]=new Platfrom(platfroms[(i+platfromNum-1)%platfromNum]);
		}
		
		for (int i=0;i<goldNum;i++) {
			goldCoins[i].paint(g);
			if (goldCoins[i].x<-goldCoins[i].WIDTH) goldCoins[i]=new GoldCoin();
		}
			
		
		g.drawImage(showfield, (int)(GameJFrame.WIDTH*0.05), (int)(GameJFrame.HEIGHT*0.02), (int)(GameJFrame.WIDTH*0.16), (int)(GameJFrame.HEIGHT*0.07),null);
		g.drawImage(showfield, (int)(GameJFrame.WIDTH*0.25), (int)(GameJFrame.HEIGHT*0.02), (int)(GameJFrame.WIDTH*0.16), (int)(GameJFrame.HEIGHT*0.07),null);
		
		g.setFont(new Font("微软雅黑", Font.BOLD, GameJFrame.WIDTH*GameJFrame.HEIGHT/40000));
		g.setColor(Color.white);
		g.drawString("得分:"+score,(int)(GameJFrame.WIDTH*0.07), (int)(GameJFrame.HEIGHT*0.07));
		g.drawString("距离:"+distance,(int)(GameJFrame.WIDTH*0.27), (int)(GameJFrame.HEIGHT*0.07));
		
	}
	
	public void action() {
		// TODO 自动生成的方法存根
		new Thread() {
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				while (true) {
					repaint();
					try {
						sleep(10);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
		if (e.getKeyCode()==KeyEvent.VK_SPACE) person.jump();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}
	
	
}
