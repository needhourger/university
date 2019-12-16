import java.util.LinkedList;

public class Food {
   LinkedList<Node> body=new LinkedList<Node>();

public LinkedList<Node> getBody() {
	return body;
}

public void setBody(LinkedList<Node> body) {
	this.body = body;
}
   public Food(int x,int y){
	   body.add(new Node(x,y));
	   body.add(new Node(x+1,y));
   }
   
}
