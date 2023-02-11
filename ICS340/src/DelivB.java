import java.io.*;
import java.util.Collections;
import java.util.Comparator;

public class DelivB {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;

	// Constructor - DO NOT MODIFY
	public DelivB(File in, Graph gr) {
		inputFile = in;
		graph = gr;

		// Set up for writing to a file
		try {
			// Use input file name to create output file in the same location
			String inputFileName = inputFile.toString();
			String outputFileName = inputFileName.substring(0, inputFileName.length() - 4).concat("_out.txt");
			outputFile = new File(outputFileName);

			// A Printwriter is an object that can write to a file
			output = new PrintWriter(outputFile);
		} catch (Exception x) {
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}

		// Calls the method that will do the work of deliverable B
		runDelivB();

		output.flush();
	}

	// *********************************************************************************
	// This is where your work starts

	private void runDelivB() {

		depthFirstSearch(graph);
		classifyEdges(graph);
		System.out.println("DFS of graph");
		output.println("DFS of graph");
		System.out.println("                        ");
		output.println("                        ");
		System.out.println("Node  Disc  Finish");
		output.println("Node  Disc  Finish");

		for (Node n : graph.getNodeList()) {
			System.out.println(n.getAbbrev() + "     " + n.getDiscoveryTime() + "     " + n.getFinishTime());
			output.println(n.getAbbrev() + "     " + n.getDiscoveryTime() + "     " + n.getFinishTime());

			
		}

	

		System.out.println("        ");
		output.println("       ");
		System.out.println("Edge Classification:");
		output.println("Edge Classification:");
		System.out.println("        ");
		output.println("        ");

		System.out.println("Edge   Type");
		output.println("Edge   Type");

		for (Edge e : graph.getEdgeList()) {
			System.out.println(e.getTail().getAbbrev() + "->" + e.getHead().getAbbrev() + "   " + e.getEdgeType());
			output.println(e.getTail().getAbbrev() + "->" + e.getHead().getAbbrev() + "   " + e.getEdgeType());
		}

	}

	private int totalTime;

	public void depthFirstSearch(Graph graph) {

		// Find start node
		Node startNode = graph.findStartNode();

		// sort so that any unconnected components are included

		Collections.sort(graph.getNodeList(), new sortNodes());

		for (Node u : graph.getNodeList()) {

			u.setColor("white");
			u.setPredecessorNode(null);

		}
		totalTime = 0;

		depthFirstSearchVisit(graph, startNode);

		for (Node u : graph.getNodeList()) {

			if (u.getColor().equalsIgnoreCase("white")) {

				depthFirstSearchVisit(graph, u);
			}
		}

	}

	public int depthFirstSearchVisit(Graph graph, Node u) {
		totalTime += 1;
		u.setDiscoveryTime(totalTime);
		u.setColor("gray");
		
		 
		u.findAdjacentNodes();

		for (Node v : u.findAdjacentNodes()) {
			

			if (v.getColor().equalsIgnoreCase("white")) {

				v.setPredecessorNode(u);

				u.findEdges(v).setEdgeType("Tree");

				depthFirstSearchVisit(graph, v);
				
				
			}

		}

		u.setColor("black");
		totalTime += 1;
		u.setFinishTime(totalTime);

		return totalTime;
	}

	public void classifyEdges(Graph graph) {
		
		for(Edge e : graph.getEdgeList()) {
			
			if (e.getEdgeType() == null) {
				Node u = e.getTail();
				Node v = e.getHead();
				
				if(v.getDiscoveryTime() < v.getFinishTime() && v.getFinishTime() < u.getDiscoveryTime() && u.getDiscoveryTime() < u.getFinishTime()) {
					e.setEdgeType("Cross");
				}else if(u.getDiscoveryTime() > v.getDiscoveryTime() && u.getFinishTime() < v.getFinishTime()) {
					e.setEdgeType("Back");
				}else {
					e.setEdgeType("Forward");
				}
			}
		} 

}

public class sortNodes implements Comparator<Node> {

	@Override
	public int compare(Node n1, Node n2) {
		return n1.getAbbrev().compareToIgnoreCase(n2.getAbbrev());

	}
}}
