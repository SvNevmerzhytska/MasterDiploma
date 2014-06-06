package edu.kpi.master.algorithm;

import javax.swing.JOptionPane;

import edu.kpi.master.datatypes.Graph;
import edu.kpi.master.datatypes.Vertex;
import edu.kpi.master.gui.helper.Utils;

public class FeasibleSolution {

	public static Graph graph = new Graph();
	private static boolean possibleToContinue = true;
	
	public static void findFeasibleSolution(){
		initialize();
		if(possibleToContinue) {
			step1();
			Utils.window.getProgressBar().setValue(1);
		}
		if(possibleToContinue) {
			step2();
			Utils.window.getProgressBar().setValue(2);
		}
		if(possibleToContinue) {
			step3();
			Utils.window.getProgressBar().setValue(3);
		}
		if(possibleToContinue) {
			step4();
			Utils.window.getProgressBar().setValue(4);
		}
	}
	
	//copy initial data from preset solution
	public static void initialize() {
		graph.setVertexes(PresetSolution.graph.getVertexes());
		for(Vertex vertex : graph.getVertexes()) {
			if(vertex.getInArcs().size() == 0) {
				possibleToContinue = false;
				JOptionPane.showMessageDialog(null, "Can not find feasible solution.", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(vertex.getOutArcs().size() == 0) {
				possibleToContinue = false;
				JOptionPane.showMessageDialog(null, "Can not find feasible solution.", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		graph.setArcs(PresetSolution.graph.getArcs());
	}
	
	//step 1
	public static void step1() {
		
	}
	
	//step 2
	public static void step2() {
		
	}
	
	//step 3
	public static void step3() {
		
	}
	
	//step 4
	public static void step4() {
		
	}
}
