import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;



public class Grid {
	public final int status[][];
	private final int width;
	private final int height;
	private long plus;
	private Person person;
	private LinkedList<Barrier> barriers=new LinkedList<Barrier>();
	private long time;
	private LinkedList<Food> foods=new LinkedList<Food>();
	
	public LinkedList<Barrier> getBarriers() {
		return barriers;
	}


	private Direction personDirection=Direction.RIGHT;
	
	public Grid(int width,int height){
		this.width=width;
		this.height=height;
		status=new int[width][height];
		init();
		plus=0;
		
	}
	
	public Direction getPersonDirection() {
		return personDirection;
	}
	
	public void changeDirection(Direction newDirection){
		personDirection=newDirection;
	}

	public void setPersonDirection(Direction snakeDirection) {
		this.personDirection = snakeDirection;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public void CreateFood(){
		int x,y;
		do{
			Random r=new Random();
			x=r.nextInt(width);
			y=r.nextInt(height);
		}while(status[x][y]==1 || y>=height-2 || y<=1 || x>=width-1);
		status[x][y]=2;
		status[x+1][y]=2;
		
	   foods.add(new Food(x,y));
	}

	public LinkedList<Food> getFoods() {
		return foods;
	}

	public void setFoods(LinkedList<Food> foods) {
		this.foods = foods;
	}

	public void init(){
		for(int i=0;i<width;i++){
			Arrays.fill(status[i], 0);
		}
		time=System.currentTimeMillis();
		initPerson();
		barriers.clear();
		
		barriers.addLast(initDownBarrier(width*4/10,height*4/5));
		
		barriers.addLast(initBarrier(width*6/10,height/3));
		
		barriers.addLast(initDownBarrier(width*8/10,height/2));
		barriers.addLast(initBarrier(width-1,height/3));

	}
	
	
	
	public long getPlus() {
		return plus;
	}


	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Person initPerson(){
		person=new Person(width/10,height-3);
		
		return person;
	}
	
	public Barrier initBarrier(int x,int y){
		Barrier barrier=new Barrier();
		for(int i=0;i<y;i++){
			Node node=new Node(x,i);
			barrier.addBody(node);
			status[x][i]=1;
		}
		return barrier;

	}
	
	public Barrier initDownBarrier(int x,int y){
		Barrier barrier=new Barrier();
		for(int i=height-1;i>=y;i--){
			Node node=new Node(x,i);
			barrier.addBody(node);
			status[x][i]=1;
		}
		return barrier;

	}
	

	
	public  Barrier createBarrier(){
		Random rand=new Random();
		if(rand.nextInt(2)==1){
			return initBarrier(width-1,rand.nextInt(height*1)/2+1);
		}
		else return initDownBarrier(width-1,height-rand.nextInt(height*2/5)-2);
	}
	
	public void FoodsMove(){
		Iterator<Food> it=foods.iterator();
		while(it.hasNext()){
			Food food=it.next();
			for(Node node:food.getBody()){
			status[node.getX()][node.getY()]=0;
			node.setX(node.getX()-1);
			}
			if(food.getBody().getFirst().getX()<0)it.remove();
			else for(Node node:food.getBody()){
				status[node.getX()][node.getY()]=2;
			}
		}
	}
	
	public void BarriersMove(){
		Iterator<Barrier> it=barriers.iterator();
		boolean mark=false;
		while(it.hasNext()){
			
			Barrier barrier=it.next();
			for(Node node:barrier.getBody())
				status[node.getX()][node.getY()]=0;
				if(!barrier.move()){
					it.remove();
					mark=true;
				}
				else{for(Node node:barrier.getBody())
					status[node.getX()][node.getY()]=1;
					
				}
		}
		if(mark==true)barriers.addLast(createBarrier());
		}
	
	public void addPlus(){
		for(Node node:person.getPoisiton()){
			status[node.getX()][node.getY()]=0;
			status[node.getX()+1][node.getY()]=0;
		}
		
		Iterator<Food> it=foods.iterator();
		while(it.hasNext()){
			Food food=it.next();
				Node na=food.getBody().getFirst();
				Node nb=food.getBody().getLast();
			
			if(status[na.getX()][na.getY()]==0 || status[nb.getX()][nb.getY()]==0 ){
				status[na.getX()][na.getY()]=0;
				status[nb.getX()][nb.getY()]=0;
			
				it.remove();
		}
			}
		plus+=5;
	}
	public boolean personOnGround(){
		return person.OnGround();
	}
	
	public boolean collision(){
		for(Node node:person.getPoisiton()){
			if(status[node.getX()][node.getY()]==1 || status[node.getX()+1][node.getY()]==1)return true;
		}
		return false;
	}
	
	public boolean eatFood(){
		for(Node node:person.getPoisiton()){
			if(status[node.getX()][node.getY()]==2 || status[node.getX()+1][node.getY()]==2)return true;
		}
		return false;
	}
	public boolean nextRound(){
		person.setPosition(person.move(personDirection));
	
		
		if(collision())return false;
		if(eatFood()){
			addPlus();
		}
		BarriersMove();
		FoodsMove();
		Random rand=new Random();
		if(rand.nextInt(100)<=3)
			CreateFood();
		return true;
	}
	

}
