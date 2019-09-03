package com.four.bird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class World extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1090556378108486037L;
	Column column1;
	Column column2;
	Bird bird;
	Ground ground;
	BufferedImage background;
	BufferedImage gameoverImg;
	BufferedImage startImg;
	
	boolean start;
	int score;
	boolean gameOver;
	
	int index = 0;

	public World() throws IOException {
		background = ImageIO.read(getClass().getResource("bg.png"));
		gameoverImg = ImageIO.read(getClass().getResource("gameover.png"));
		startImg = ImageIO.read(getClass().getResource("start.png"));
		start();
	}
	public void start(){
		try {
			start = false;
			gameOver = false;
			bird = new Bird();
			ground = new Ground(); 
			column1 = new Column(1);
			column2 = new Column(2);
			score = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void action() throws Exception{		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (gameOver) {
					start();
					return;
				}
				start = true;
				bird.flappy();
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_SPACE){
					if(gameOver){
						start();
						return;
					}
					start = true;
					bird.flappy();
				}
			}
		});
		requestFocus();
		//主循环, 时间间隔是 1/60 秒
		while(true){
			if(start && !gameOver){
				bird.step();
				column1.step();
				column2.step();
				//检查是否通过柱子了
				if(bird.pass(column1, column2)){
					score++;
				}
				if(bird.hit(column1, column2, ground)){
					start = false;
					gameOver = true;
				}
			}
			if(! gameOver) bird.fly();
			ground.step();
			repaint();
			Thread.sleep(1000/70);
		}
				
	}
	@Override
	public void paint(Graphics g) {
		//抗锯齿代码
//		Graphics2D g2 = (Graphics2D)g;
//		RenderingHints qualityHints = new RenderingHints(
//				RenderingHints.KEY_ANTIALIASING,
//				RenderingHints.VALUE_ANTIALIAS_ON);
//		qualityHints.put(RenderingHints.KEY_RENDERING,
//				RenderingHints.VALUE_RENDER_QUALITY);
//		g2.setRenderingHints(qualityHints);
		//绘制背景
		g.drawImage(background, 0, 0, null);
		//绘制柱子
		column1.paint(g);
		column2.paint(g); 
		//绘制地面
		ground.paint(g);
		//绘制分数
		Font font = new Font(Font.MONOSPACED, Font.BOLD, 45);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(score+"", 30, 50);
		//绘制小鸟
		bird.paint(g);
		//绘制结束状态
		if(gameOver){
			//g.drawString("Game Over!", 70 , 190);
			g.drawImage(gameoverImg, 0, 0, null);
			return;
		}
		if(! start){
			//g.drawString("Start >>>", bird.x+35, bird.y);
			g.drawImage(startImg, 0, 0, null);
		}
	}
	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("飞扬小鸟");
		World world = new World();
		frame.add(world);
		frame.setSize(432+8, 644+30);
		//frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		world.action();
	}
}
