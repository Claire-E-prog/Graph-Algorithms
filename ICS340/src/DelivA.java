import java.io.*;
import java.util.Collections;
import java.util.Comparator;

public class DelivA {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;

	// Constructor - DO NOT MODIFY
	public DelivA(File in, Graph gr) {
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

		// Calls the method that will do the work of deliverable A
		runDelivA();

		output.flush();
	}

	// *********************************************************************************
	// This is where your work starts

	private void runDelivA() {


		// Print nodes/headers and associated edge values
		System.out.println("Print the nodes and and associated edges");

		for (Node n : graph.getNodeList()) {

			System.out.println(n.getAbbrev() + " " + n.getIncomingEdges());// Prints to console

		}

		// Print nodes and associated in-degree

		System.out.println("");

		System.out.println("Print the nodes and number of incoming edges");

		for (Node n : graph.getNodeList()) {

			System.out.println(n.getAbbrev() + " " + n.getIncomingEdges().size());// Prints to console

		}

		// Print nodes and associated out-degree
		System.out.println("");

		System.out.println("Print the nodes and number of outgoing edges");

		for (Node n : graph.getNodeList()) {

			System.out.println(n.getAbbrev() + " " + n.getOutgoingEdges().size());// Prints to console

		}

		/*
		 * Actual deliverable: print nodes in order of in-degree - this uses the
		 * NodeCompaerer compare() I have a second method in NodeComparer that sorts
		 * out-degree but I don't know how to switch back and forth between methods...
		 * 
		 */
		
		//Sort incoming
		Collections.sort(graph.getNodeList(), new NodeComparer());
		
		output.println("");

		System.out.println("Indegree:");
		output.println("Indegree:");

		for (Node n : graph.getNodeList()) {

			System.out.println("Node " + n.getAbbrev() + " has indegree " + n.getIncomingEdges().size());// Prints to
																											// console
			output.println("Node " + n.getAbbrev() + " has indegree " + n.getIncomingEdges().size());// Prints to file
		}

		//Sort outgoing
		Collections.sort(graph.getNodeList(), new otherNodeComparer());
		
		System.out.println("");
		output.println("");
		System.out.println("Outdegree:");
		output.println("Outdegree:");

		for (Node n : graph.getNodeList()) {

			System.out.println("Node " + n.getAbbrev() + " has outdegree " + n.getOutgoingEdges().size());// Prints to
																											// console
			output.println("Node " + n.getAbbrev() + " has outdegree " + n.getOutgoingEdges().size());// Prints to file
		}
	}

	//indegree comparer
	private class NodeComparer implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {

			if (o1.getIncomingEdges().size() == o2.getIncomingEdges().size()) {
				return o2.getOutgoingEdges().size() - o1.getOutgoingEdges().size();
			}
			else if(o1.getIncomingEdges().size() == o2.getOutgoingEdges().size()) {
				return o1.getAbbrev().compareTo(o2.getAbbrev());
			}
			return o2.getIncomingEdges().size() - o1.getIncomingEdges().size();

		}
	}
	
	//outdegree comparer

	private class otherNodeComparer implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {

			if (o1.getOutgoingEdges().size() == o2.getOutgoingEdges().size()) {
				return o2.getIncomingEdges().size() - o1.getIncomingEdges().size();
			}
			else if(o1.getIncomingEdges().size() == o2.getOutgoingEdges().size()) {
				return o1.getAbbrev().compareTo(o2.getAbbrev());
				
			}
			return o2.getOutgoingEdges().size() - o1.getOutgoingEdges().size();
		}

	}

}
