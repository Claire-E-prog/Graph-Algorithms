import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class DelivC {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;

	// Constructor - DO NOT MODIFY
	public DelivC(File in, Graph gr) {
		inputFile = in;
		graph = gr;

		// Set up for writing to a file
		try {
			// Use input file name to create output file in the same location
			String inputFileName = inputFile.toString();
			String outputFileName = inputFileName.substring(0, inputFileName.length() - 4).concat("_out.txt");
			outputFile = new File(outputFileName);

			// A Print writer is an object that can write to a file
			output = new PrintWriter(outputFile);
		} catch (

		Exception x) {
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}

		// Calls the method that will do the work of deliverable C
		runDelivC();

		output.flush();

	}

	// *********************************************************************************
	// This is where your work starts

	private void runDelivC() {
		// Delete these lines when you add functionality
		
		Node r = graph.findStartNode();//find startnode
		
		MinSpanTreePrim(graph, r);
		
		System.out.println("The minimum spanning tree has a total cost of " + sumKeys(graph) + " and includes the following edges:");
		output.println("The minimum spanning tree has a total cost of " + sumKeys(graph) + " and includes the following edges:");
		
		for (Node n : graph.getNodeList()) {
			if(n.getPredecessorNode()!= null) {
			
				System.out.println(n.getAbbrev() + "-" + n.getPredecessorNode());
				output.println(n.getAbbrev() + "-" +  n.getPredecessorNode());
			}
		}
		
	}

	
	
	public void MinSpanTreePrim(Graph g, Node r) {
		for (Node u : graph.getNodeList()) {
			u.setKey(Integer.MAX_VALUE);
			u.setPredecessorNode(null);
		}
		r.setKey(0);
		PriorityQueue<Node> q = graph.nodeQueue(graph.getNodeList());
		
		while (!q.isEmpty()) {
			Node u = q.remove();
			for (Node v : u.findAdjacentNodes()) {
				int w = graph.findEdgeLength(u, v);
				if (q.contains(v) && w < v.getKey()) {
					v.setPredecessorNode(u);
					v.setKey(w);
				q.remove(v);
				q.add(v);
		
				}
			}


		}

	}
	public int sumKeys(Graph g) {
		int s = 0;
		for(Node n : graph.getNodeList()) {
			s += n.getKey();
			
		}
		return s;
		
	}
	

}
