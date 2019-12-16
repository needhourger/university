import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App implements Runnable{
	Grid grid;
	GameView gameView;
	GameController gameController;
	public void run(){
		grid=new Grid(Settings.DEFAULT_GRID_WIDTH / Settings.DEFAULT_NODE_SIZE,Settings.DEFAULT_GRID_HEIGHT / Settings.DEFAULT_NODE_SIZE);
		JFrame window=new JFrame("ÃÏÃÏø·≈‹ RunEveryDay");
		Container contentPane=window.getContentPane();
		gameView=new GameView(grid);
		gameView.init();
		gameView.getCanvas().setPreferredSize(new Dimension(Settings.DEFAULT_GRID_WIDTH, Settings.DEFAULT_GRID_HEIGHT));
		contentPane.add(gameView.getCanvas(), BorderLayout.CENTER);
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		gameController=new GameController(grid,gameView);
		window.addKeyListener(gameController);
		new Thread(gameController).start();
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new App());
	}

}
