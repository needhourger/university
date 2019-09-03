import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements Runnable,KeyListener{
	private final Grid grid;
	private final GameView gameView;
	private int jump;
	private int doubleJump;
	private int go;
	long time=System.currentTimeMillis();
	boolean running;
	public GameController(Grid grid,GameView gameView){
		this.grid=grid;
		this.gameView=gameView;
		this.running=true;
		this.jump=0;
		this.doubleJump=0;
		this.go=0;
	}
	
	public void keyPressed(KeyEvent e){
		int keyCode=e.getKeyCode();
		if (keyCode==KeyEvent.VK_UP){
			if(grid.personOnGround()){
				jump=4;
				grid.changeDirection(Direction.UP);
			}
			else if(doubleJump==0){
				doubleJump=1;
				jump+=3;
				grid.changeDirection(Direction.UP);
			}
		}
		
		else if (keyCode==KeyEvent.VK_RIGHT){
			if(grid.personOnGround() && go==0){
			grid.changeDirection(Direction.RIGHT);
			go=1;
		}
		}
		else if(keyCode==KeyEvent.VK_LEFT){
			if(grid.personOnGround() && go==0){
			grid.changeDirection(Direction.LEFT);
			go=1;
			}
		}
		else if (keyCode==KeyEvent.VK_SPACE){
			if(running==true)running=false;
			else if(running==false){
				running=true;
				new Thread(this).start();
			}
		}
		else if(keyCode==KeyEvent.VK_ENTER){
		
			if(running==false){
				running=true;
				grid.init();
				new Thread(this).start();
			}
		}
		time=System.currentTimeMillis();
	}
   
    public void keyReleased(KeyEvent e) {
     }
     
    public void keyTyped(KeyEvent e) {
     }
	public void run(){
		while(running){
			try{
				Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
			}catch(InterruptedException e){
				break;
			}
			if(!grid.nextRound()){
                gameView.showGameOverMessage();
				break;
			}
			
			if(jump==0 && go==0)grid.changeDirection(Direction.DOWN);
			else if(jump!=0)jump--;
			if(go!=0)go--;
			if(grid.personOnGround())doubleJump=0;
			
			gameView.draw();
			if(gameView.getBg1()==-grid.getWidth()*Settings.DEFAULT_NODE_SIZE)
				gameView.setBg1(grid.getWidth()*Settings.DEFAULT_NODE_SIZE);
			if(gameView.getBg2()==-grid.getWidth()*Settings.DEFAULT_NODE_SIZE)
				gameView.setBg2(grid.getWidth()*Settings.DEFAULT_NODE_SIZE);
		}
		running=false;
	}
	

}
