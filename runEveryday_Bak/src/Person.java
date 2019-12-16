import java.util.LinkedList;

public class Person {
    private LinkedList<Node> position=new LinkedList<Node>();
    
    public Person(int x,int y){
    	position.addFirst(new Node(x,y));
    	position.addLast(new Node(x,y-1));
    }
    @SuppressWarnings("incomplete-switch")
	public LinkedList<Node> move(Direction direction){
    	int x=position.getFirst().getX();
    	int y=position.getFirst().getY();
    	switch(direction){
    	case UP:{
    		y-=2;
    		break;
    	}
    		
    	case RIGHT:
    	{	if(x<Settings.DEFAULT_GRID_WIDTH/Settings.DEFAULT_NODE_SIZE-1 && y==Settings.DEFAULT_GRID_HEIGHT/Settings.DEFAULT_NODE_SIZE-3)x+=2;
    	  
    		break;}
    	case LEFT:{
    		if(x>0 && y==Settings.DEFAULT_GRID_HEIGHT/Settings.DEFAULT_NODE_SIZE-3)x-=2;
    		
    	    break;}
    	case DOWN:
    		y++;
    		break;
    	case DOUBLEDOWN:
    		y+=2;
    		break;
    	
    	}
    	if(y>Settings.DEFAULT_GRID_HEIGHT/Settings.DEFAULT_NODE_SIZE-3)
    		y=Settings.DEFAULT_GRID_HEIGHT/Settings.DEFAULT_NODE_SIZE-3;
    	else if(y<1)y=1;
    	position.clear();
    	position.addLast(new Node(x,y));
    	position.addLast(new Node(x,y-1));
    	return position;
    }
    	
    	public LinkedList<Node> getPoisiton(){
    		return position;
    	}
    	public void setPosition(LinkedList<Node> position){
    		this.position=position;
  
    	}
    	public boolean OnGround(){
    		if (position.getFirst().getY()==Settings.DEFAULT_GRID_HEIGHT/Settings.DEFAULT_NODE_SIZE-3)
    			return true;
    		return false;
    	}
    }

