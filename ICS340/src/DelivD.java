import java.io.*;
import java.util.Collections;
import java.util.Comparator;

public class DelivD {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;

	// Constructor - DO NOT MODIFY
	public DelivD(File in, Graph gr) {
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

		// Calls the method that will do the work of deliverable D
		runDelivD();

		output.flush();
	}

	// *********************************************************************************
	// This is where your work starts

	private void runDelivD() {
		
		Collections.sort(graph.getNodeList(), new valComparer());
		
		
		int n = graph.getNodeList().size() - 1;
		int w = bitonic(n-1,n) + findDistance(n-1, n);
		
		System.out.println("Shortest bitonic tour has distance " + w);
		output.println("Shortest bitonic tour has distance " + w);
		
		System.out.print("Tour is ");
		output.print("Tour is ");
		for (Node node : graph.getNodeList()) {
			System.out.print(node.getAbbrev() + " -> ");//Print correct order (try linked list)
			output.print(node.getAbbrev() + " -> ");
		}
		
		

	}

	public int bitonic(int i, int j) {
		if (i == 0 && j == 1) {
			return findDistance(i, j);
		}

		else if (i < j - 1) {
			return bitonic(i, j - 1) + findDistance(j - 1, j);
		}

		else {
			// Loop through k to find the minimum value and return it
			int minimumVal = Integer.MAX_VALUE;

			for (int k = 0; k < i; k++) {

				int current = bitonic(k, j - 1) + findDistance(k, j);

				if (current < minimumVal) {
					minimumVal = current;
				}

			}
			return minimumVal;
			
		}
		
		
	}

	// Find distance between nodes given their indices
	private int findDistance(int i, int j) {
		Node n1 = graph.getNodeList().get(i);
		Node n2 = graph.getNodeList().get(j);

		return graph.findEdgeLength(n1, n2);

	}

// Sort nodes by "value" attribute
	class valComparer implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			double val1 = Double.parseDouble(o1.getValue());
			double val2 = Double.parseDouble(o2.getValue());

			return Double.compare(val1,val2);
			
		}//Fix this to read integers - double.parseDouble

	}

	
}
