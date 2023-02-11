import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

public class DelivE {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;

	// Constructor - DO NOT MODIFY
	public DelivE(File in, Graph gr) {
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

		// Calls the method that will do the work of deliverable E
		runDelivE();

		output.flush();
	}

	// *********************************************************************************
	// This is where your work starts

	private void runDelivE() {
		

		summary(findBest(graph));
		for(Node n: graph.getNodeList()) {
			System.out.print("->" + n.getAbbrev());
		}
		
	}

////////////////////////////////////////////////////////////////////////////////////
	public int simple(Graph g) {

		int totalTrip = 0;
		for (int i = 0; i < 47; i++) {

			int distance = g.findEdgeLength(g.getNodeList().get(i), g.getNodeList().get(i + 1));
			totalTrip += distance;

		}
		totalTrip += g.findEdgeLength(g.getNodeList().get(47), g.getNodeList().get(48));
		totalTrip += g.findEdgeLength(g.getNodeList().get(48), g.getNodeList().get(0));

		return totalTrip;
	}

	/*
	 * public ArrayList<Node> swapOrder(Graph g) { Collections.swap(g.getNodeList(),
	 * 1, 2); return g.getNodeList(); }
	 */

	public ArrayList<Node> shuffleOrder(Graph g) {
		Collections.shuffle(g.getNodeList());

		return g.getNodeList();

	}

	public ArrayList<Integer> findBest(Graph g) {
		ArrayList<Integer> routes = new ArrayList<Integer>();
		
		for (int i = 0; i < 5; i++) {

			shuffleOrder(g);

			for (Node n : g.getNodeList()) {
				System.out.print("->" + n.getAbbrev());
				output.print("->" + n.getAbbrev());
				

			}
			System.out.println(" " + simple(g) + " shuffle" + "\n");
			output.print(" " + simple(g) + " shuffle" + "\n");
			routes.add(simple(g));
		}
		return routes;

	}
	public int summary(ArrayList<Integer> routes) {
		int min = routes.get(0);
		for(int i = 1; i < routes.size(); i++) {
			if(min > routes.get(i)) {
				min = routes.get(i);
			}
		
			//routes.remove(routes.indexOf(max));
		}
		System.out.println("\n" + "shortest path found has distance " + min + " after 5 steps and follows the path:" );
		output.println("\n" + "shortest path found has distance " + min + " after 5 steps and follows the path:" );
		return min;
		
	}

}
