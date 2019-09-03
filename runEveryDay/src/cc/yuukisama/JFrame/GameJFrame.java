package cc.yuukisama.JFrame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import cc.yuukisama.JPanel.GameJPanel;

public class GameJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -508934813567413532L;
	
	public static final int WIDTH=1400;
	public static final int HEIGHT=800;
	public static GameJPanel panel=null;
	
	public GameJFrame() {
		// TODO 自动生成的构造函数存根
		panel=new GameJPanel();
		panel.action();
		
		this.add(panel);
		this.setSize(WIDTH,HEIGHT);
		this.setTitle("天天酷跑");
		this.setIconImage(new ImageIcon("resource/image/115.png").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		
		this.setVisible(true);
		panel.requestFocus();
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		GameJFrame xFrame=new GameJFrame();
	}

}
