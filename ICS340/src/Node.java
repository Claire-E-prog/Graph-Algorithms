import java.util.*;

// A node of a graph for the Spring 2018 ICS 340 program

public class Node {

	private String name;
	private String value; // The value of the Node which was stored in the value column
	private String abbrev; // The abbreviation for the Node
	private String color;
	private int discoveryTime;
	private int finishTime;
	private Node predecessorNode;
	private int key;

	private ArrayList<Edge> outgoingEdges;
	private ArrayList<Edge> incomingEdges;

	public Node(String abbreviation) {
		abbrev = abbreviation;
		value = null;
		name = null;
		outgoingEdges = new ArrayList<Edge>();
		incomingEdges = new ArrayList<Edge>();
	}

	/////////////////////////////////// FIND Adjacent nodes
	public ArrayList<Node> findAdjacentNodes() {

		ArrayList<Node> adjacentNodes = new ArrayList<Node>();

		for (Edge edge : this.getOutgoingEdges()) {

			adjacentNodes.add(edge.getHead());

		}
		Collections.sort(this.getOutgoingEdges(), new choosePath());
		return adjacentNodes;

	}

	private class choosePath implements Comparator<Edge> {

		@Override
		public int compare(Edge n1, Edge n2) {

			if (n1.getDistance() == n2.getDistance()) {
				return n1.getHead().getName().compareToIgnoreCase(n2.getHead().getName());
			} 
				return n1.getDistance() - n2.getDistance();
			}
		}
	

	public Edge findEdges(Node node) {
		for (Edge e : this.getOutgoingEdges()) {
			if (e.getHead().equals(node)) {
				return e;
			}

		}
		return null;

	}
	
	public String getAbbrev() {
		return abbrev;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public String getColor() {
		return color;
	}

	public ArrayList<Edge> getOutgoingEdges() {
		return outgoingEdges;
	}

	public ArrayList<Edge> getIncomingEdges() {
		return incomingEdges;
	}

	public void setAbbrev(String abbreviation) {
		abbrev = abbreviation;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void addOutgoingEdge(Edge e) {
		outgoingEdges.add(e);
	}

	public void addIncomingEdge(Edge e) {
		incomingEdges.add(e);
	}

	public String toString() {
		// return "For header: " + name + " The node is: " + abbrev;
		return name;

	}

//move these
	public int getDiscoveryTime() {
		return discoveryTime;
	}

	public void setDiscoveryTime(int discoveryTime) {
		this.discoveryTime = discoveryTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public Node getPredecessorNode() {
		return predecessorNode;
	}

	public void setPredecessorNode(Node predecessorNode) {
		this.predecessorNode = predecessorNode;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

}
