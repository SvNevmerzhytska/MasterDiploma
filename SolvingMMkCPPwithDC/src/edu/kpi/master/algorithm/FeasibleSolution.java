package edu.kpi.master.algorithm;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import edu.kpi.master.datatypes.Arc;
import edu.kpi.master.datatypes.Graph;
import edu.kpi.master.datatypes.Path;
import edu.kpi.master.datatypes.Vertex;
import edu.kpi.master.gui.helper.Utils;

public class FeasibleSolution {

	public static Graph graph = new Graph();
	private static boolean possibleToContinue = true;
	private static Set<Path> simpleRouts;
	public static long computationTime;
	public static long maxCost;
	
	public static void findFeasibleSolution(){
		computationTime = System.currentTimeMillis();
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
		}
		maxCost = 0;
		for(Path path : graph.getPathes()) {
			if(path.getCost() > maxCost) {
				maxCost = path.getCost();
			}
		}
		computationTime = System.currentTimeMillis() - computationTime;
		Utils.window.getProgressBar().setValue(4);
	}
	
	//copy initial data from preset solution
	public static void initialize() {
		graph = new Graph();
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
		graph.setDepo(PresetSolution.graph.getDepo());
		graph.setNVehicles(PresetSolution.graph.getNVehicles());
	}
	
	//step 1
	public static void step1() {
		System.out.println("Step 1. Finding simple routes for each arc...");
		simpleRouts = new HashSet<Path>();
		DijkstraShortestPath dsp = new DijkstraShortestPath(graph.getVertexes(), graph.getArcs());
		for(Arc arc : graph.getArcs()) {
			//rout from depot to begin vertex of arc
			Path path = dsp.findShortestPath(graph.getDepo(), arc.getBeginNode());
			//add current arc to the path and calculate all necessary properties
			path.setCost(path.getCost() + arc.getServiceCost());
			arc.setServiceTime((int) path.getCost());
			//check feasibility of found routes
			if(arc.getReserve() < 0) {
				possibleToContinue = false;
				System.out.println("Arc " + arc + " cannot be served in feasible time.");
				JOptionPane.showMessageDialog(null, "Can not find feasible solution.", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			Path.PathArc pathArc = path.new PathArc();
			pathArc.arc = arc;
			pathArc.servicing = true;
			path.getPathArcs().add(pathArc);
			//rout from end vertex of arc to depot
			Path path2 = dsp.findShortestPath(arc.getEndNode(), graph.getDepo());
			path.setCost(path.getCost() + path2.getCost());
			path.getPathArcs().addAll(path2.getPathArcs());
			simpleRouts.add(path);
			System.out.println(path);
		}
		//check stop condition
		if(graph.getPathes().size() <= graph.getNVehicles()) {
			System.out.println("Feasible solution was find on step 1.");
			graph.setPathes(simpleRouts);
			possibleToContinue = false;
			return;
		}
		System.out.println();
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
