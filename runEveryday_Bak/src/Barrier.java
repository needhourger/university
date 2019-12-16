import java.util.LinkedList;

public class Barrier {
	private final LinkedList<Node> body=new LinkedList<Node>();

	public LinkedList<Node> getBody() {
		return body;
	}
	
	public void addBody(Node node){
		body.add(node);
	}
	
	public boolean move(){
		
			for(Node node:body)
				node.setX(node.getX()-1);
			if(body.getFirst().getX()<0)return false;
			return true;
	}
}

