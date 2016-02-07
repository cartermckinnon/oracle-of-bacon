
public class GraphNode{

	private String myValue;
	private boolean visited;
	private GraphNode pre;
	
	public GraphNode( String aValue ){
		myValue = aValue;
		visited = false;
		pre = null;
	}
	
	public String getValue(){
		return myValue;
	}
	
	public GraphNode getPre(){
		return pre;
	}
	
	public void setPre( GraphNode aPre ){
		pre = aPre;
	}
	
	public void setVisited( boolean b ){
		visited = b;
	}
	
	public boolean visited(){
		return visited;
	}
	
	public String toString(){
		return myValue;
	}
	
}
