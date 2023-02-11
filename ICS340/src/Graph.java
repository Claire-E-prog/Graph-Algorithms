import java.util.*;


public class Graph {

	private ArrayList<Node> nodeList;
	private ArrayList<Edge> edgeList;
	private Node startNode;

	public Graph() {
		nodeList = new ArrayList<Node>();
		edgeList = new ArrayList<Edge>();
	}
	// method to find start node

	public Node findStartNode() {
		for(Node n: nodeList) {
			if(n.getValue().equalsIgnoreCase("S")){
			return n;
			}
		}
		return null;
	}
	
	public Node findStartCityNode() {
		Collections.shuffle(nodeList);
		return nodeList.get(0) ;
	}
	
	public int findEdgeLength(Node u, Node v) {
		int w = 0;
		for(Edge e: edgeList) {
			if(e.getHead().equals(u)&& e.getTail().equals(v)){
			w =  e.getDistance();
			}
		}
		return w;
		
		
	}
	
	

	public PriorityQueue<Node> nodeQueue(ArrayList<Node> nodeList) {
		PriorityQueue<Node> prQueue = new PriorityQueue<Node>(nodeList.size(), new keyComparator() );//send a comparator and the size;
		for(Node u: nodeList) {
			prQueue.add(u);
		}
		
		
		return prQueue;
		
		
	}
	
	
	private class keyComparator implements Comparator <Node> {

		@Override
		public int compare(Node o1, Node o2) {
			if(o1.getKey() == o2.getKey()) {
				
			return o1.getName().compareTo(o2.getName());
		}
			return o1.getKey() - o2.getKey();
	
		}
		
	}
		

	public ArrayList<Node> getNodeList() {
		return nodeList;
	}

	public ArrayList<Edge> getEdgeList() {
		return edgeList;
	}

	public void addNode(Node n) {
		nodeList.add(n);
	}

	public void addEdge(Edge e) {
		edgeList.add(e);
	}
	
	public Node getStartNode() {
		return startNode;
	}

}
