import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameView {
	private final Grid grid;
	private JPanel canvas;
	private long time;
    private long score;
    private int bg1;
    private int bg2;
	String PersonImgPath = "..//runEveryday//img//person.png";  
	String BarrierImgPath="..//runEveryday//img//barrier.png";
	String FruitImgPath="..//runEveryday//img//fruit.png";
	String BackGroundImgPath="..//runEveryday//img//back1.png";
	Image PersonImage=Toolkit.getDefaultToolkit().getImage(PersonImgPath);
	Image BarrierImg=Toolkit.getDefaultToolkit().getImage(BarrierImgPath);
	Image FruitImg=Toolkit.getDefaultToolkit().getImage(FruitImgPath);
	Image BackGroundImg=Toolkit.getDefaultToolkit().getImage(BackGroundImgPath);
	public GameView(Grid grid){
		this.grid=grid;
		time=System.currentTimeMillis();
		bg1=0;
		bg2=grid.getWidth()*Settings.DEFAULT_NODE_SIZE;
	
		
	}
	
	public void init(){
		canvas=new JPanel(){
			public void paintComponent(Graphics graphics){
				bg1=bg1-5;
				bg2=bg2-5;
				drawGridBackground(graphics);
				drawPerson(graphics,grid.getPerson());
				drawBarriers(graphics,grid.getBarriers());
				drawFoods(graphics,grid);
				drawScore(graphics,grid);
			    
			
			
			}
		};
	}
	
	public void draw(){
		canvas.repaint();
	
	}
	
	public long getScore(){
		return (System.currentTimeMillis()-time)/300+grid.getPlus();
	}
	
	public JPanel getCanvas() {
		return canvas;
	}

	public void setCanvas(JPanel canvas) {
		this.canvas = canvas;
	}

	public void drawPerson(Graphics graphics,Person person){

		Node node=person.getPoisiton().getFirst();
		graphics.drawImage(PersonImage,node.getX()*Settings.DEFAULT_NODE_SIZE-80,node.getY()*Settings.DEFAULT_NODE_SIZE-100,150,150, null);
	}
	

	public void drawScore(Graphics graphics,Grid grid){
		
		graphics.setColor(Settings.DEFAULT_SCORE_COLOR);
		graphics.setFont(new Font("宋体",Font.BOLD,30));
		graphics.drawString("得分:"+(grid.getPlus()+getScore()),5,60);
		graphics.drawString("按上键跳跃，可二连跳，按左右移动人物，空格暂停。", 5, 30);
	}
	
	public void drawGridBackground(Graphics graphics){
		graphics.setColor(Settings.DEFAULT_BACKGROUND_COLOR);
		graphics.fillRect(0,0,grid.getWidth()*Settings.DEFAULT_NODE_SIZE,grid.getHeight()*Settings.DEFAULT_NODE_SIZE);
	
		graphics.drawImage(BackGroundImg,bg1, 0, grid.getWidth()*Settings.DEFAULT_NODE_SIZE,grid.getHeight()*Settings.DEFAULT_NODE_SIZE, null);
		graphics.drawImage(BackGroundImg,bg2, 0, grid.getWidth()*Settings.DEFAULT_NODE_SIZE,grid.getHeight()*Settings.DEFAULT_NODE_SIZE, null);
		
	}
	
	public void drawFoods(Graphics graphics,Grid grid){
		
		graphics.setColor(Settings.DEFAULT_PERSON_COLOR);
        int size=Settings.DEFAULT_NODE_SIZE;
        for(Food food:grid.getFoods()){
		Node node=food.getBody().getFirst();
		
		graphics.drawImage(FruitImg, node.getX()*Settings.DEFAULT_NODE_SIZE-10, node.getY()*Settings.DEFAULT_NODE_SIZE-20, 2*Settings.DEFAULT_NODE_SIZE, 2*Settings.DEFAULT_NODE_SIZE, null);
	}
	}
	
	
	public void drawBarrier(Graphics graphics,Barrier barrier){
		java.util.List<Node> body = barrier.getBody();
		for(Node node:body){
		drawSquare(graphics,node,Settings.DEFAULT_BARRIER_COLOR);
	}
	}
	
	public void drawBarrierPicture(Graphics graphics,Barrier barrier){
	Node anode=barrier.getBody().getFirst();
	Node bnode=barrier.getBody().getLast();
		if(anode.getY()==0){
			graphics.drawImage(BarrierImg, anode.getX()*Settings.DEFAULT_NODE_SIZE-20, anode.getY()*Settings.DEFAULT_NODE_SIZE, Settings.DEFAULT_NODE_SIZE+40, barrier.getBody().size()*Settings.DEFAULT_NODE_SIZE+15,null);
	}
		else {
			graphics.drawImage(BarrierImg, bnode.getX()*Settings.DEFAULT_NODE_SIZE-20, bnode.getY()*Settings.DEFAULT_NODE_SIZE-15, Settings.DEFAULT_NODE_SIZE+40, barrier.getBody().size()*Settings.DEFAULT_NODE_SIZE+15,null);
		}
			
		}
	
	public void drawPicture(Graphics graphics,Node node){
		
		
		graphics.drawImage(PersonImage,node.getX()*Settings.DEFAULT_NODE_SIZE,node.getY()*Settings.DEFAULT_NODE_SIZE-10-Settings.DEFAULT_NODE_SIZE,100,100, null);
	}
	
	public void drawBarriers(Graphics graphics,LinkedList<Barrier> barriers){
		for(Barrier barrier:barriers){
			drawBarrier(graphics,barrier);
			drawBarrierPicture(graphics,barrier);
		}
	}
	
	
	private void drawSquare(Graphics graphics,Node node,Color color){
		graphics.setColor(color);
		int size=Settings.DEFAULT_NODE_SIZE;
		
		graphics.fillRect(node.getX()*size, node.getY()*size, size-1, size-1);
	}
	
	private void drawCircle(Graphics graphics,Node node,Color color){
		graphics.setColor(color);
		int size=Settings.DEFAULT_NODE_SIZE;
		graphics.fillOval(node.getX()*size, node.getY()*size, size,size);
	}
	
	
	
    public void showGameOverMessage() {
 

        JOptionPane.showMessageDialog(null, "游戏结束,按回车重新开始", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
    }

	public int getBg1() {
		return bg1;
	}

	public void setBg1(int bg1) {
		this.bg1 = bg1;
	}

	public int getBg2() {
		return bg2;
	}

	public void setBg2(int bg2) {
		this.bg2 = bg2;
	}

}
