package edu.kpi.master.generator;

import javax.swing.JOptionPane;

import edu.kpi.master.datatypes.Graph;

public class Generator {
	
	public static int nVehicles;
	public static int nVertexes;
	public static int nArcs;
	public static Graph graph;
	
	//return true if generation process was successful
	public static boolean generateData() {
		//validate input data
		String message = checkInputParams();
		if(!message.isEmpty()) {
			JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		graph = new Graph();
		System.out.println("Generate data finished.");
		return true;
	}
	
	
	//return description about invalid parameters (if any)
	public static String checkInputParams() {
		String result = "";
		if(nVehicles < 1) {
			result = result + "\nNumber of vehicles (routes) should be grater than 0";
		}
		if(nVertexes <= 1) {
			result = result + "\nNumber of vertexes should be grater than 1";
		}
		if(nArcs < nVertexes + nVehicles - 1) {
			result = result + "\nNumber of arcs should be at least "
					+ ((nVehicles > 0 && nVertexes >1) ? (nVertexes + nVehicles - 1) : "vertexes + vehicles - 1");
		}
		return result;
	}

}
