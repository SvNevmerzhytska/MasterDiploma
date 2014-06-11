package edu.kpi.master.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import javax.swing.JOptionPane;

import edu.kpi.master.algorithm.DijkstraShortestPath;
import edu.kpi.master.algorithm.FeasibleSolution;
import edu.kpi.master.datatypes.Arc;
import edu.kpi.master.datatypes.Graph;
import edu.kpi.master.datatypes.Path;
import edu.kpi.master.datatypes.Path.PathArc;
import edu.kpi.master.datatypes.Vertex;

public class Generator {
	
	public static int nVehicles;
	public static int nVertexes;
	public static int nArcs;
	public static Graph graph;
	private static long maxCost;
	private static int SERVICE_COST = 2;
	private static int TRANSIT_COST = 1;
	private static int DEADLINE_STEP = 5;
	
	//return true if generation process was successful
	public static boolean generateData() {
		//validate input data
		String message = checkInputParams();
		if(!message.isEmpty()) {
			JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		System.out.println("Data generation is started...");
		//crate new graph
		graph = new Graph();
		//generate vertexes (0 - is depot)
		generateVertexes();
		//generate arcs
		generateArcs();
		//generate paths
		generatePaths();
		
		for(Arc arc : graph.getArcs()) {
			System.out.println(arc);
		}
		for(Path path : graph.getPathes()) {
			System.out.println(path);
		}
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
		if(nArcs < nVertexes) {
			result = result + "\nNumber of arcs should be at least "
					+ ((nVehicles > 0 && nVertexes >1) ? (nVertexes) : "as number of vertexes");
		}
		return result;
	}
	
	public static void generateVertexes() {
		for(int i = 0; i < nVertexes; i++) {
			Vertex vertex = new Vertex(Integer.toString(i));
			graph.getVertexes().add(vertex);
			if (i == 0) {
				graph.setDepo(vertex);
			}
		}
	}
	
	public static void generateArcs() {
		for (Vertex v1 : graph.getVertexes()) {
			for (Vertex v2 : graph.getVertexes()) {
				if(!v1.equals(v2)) {
					//arc from v1 to v2
					Arc arc = new Arc();
					arc.setBeginNode(v1);
					arc.setEndNode(v2);
					arc.setServiceCost(SERVICE_COST);
					arc.setTransitCost(TRANSIT_COST);
					graph.getArcs().add(arc);
				}
			}
		}
		//remove unnecessary arcs
		ArrayList<Arc> arcs = new ArrayList<Arc>(graph.getArcs());
		Random random = new Random();
		DijkstraShortestPath dsp = new DijkstraShortestPath(graph.getVertexes(), graph.getArcs());
		while (arcs.size() > nArcs) {
			int index = random.nextInt(arcs.size());
			Arc arc = arcs.get(index);
			if(arc.getBeginNode().getOutArcs().size() > 1 && arc.getEndNode().getInArcs().size() > 1) {
				arcs.remove(arc);
				arc.getBeginNode().getOutArcs().remove(arc);
				arc.getEndNode().getInArcs().remove(arc);
				dsp.setArcs(new HashSet<Arc>(arcs));
				//to avoid not linked circles
				if(!dsp.isShortestPathExist(graph.getDepo(), arc.getEndNode()) || !dsp.isShortestPathExist(arc.getBeginNode(), graph.getDepo())) {
					arcs.add(arc);
					arc.getBeginNode().getOutArcs().add(arc);
					arc.getEndNode().getInArcs().add(arc);
				}
			}
		}
		graph.setArcs(new HashSet<Arc>(arcs));
	}
	
	public static void generatePaths() {
		ArrayList<Path> paths = new ArrayList<Path>();
		ArrayList<Arc> arcsInPaths = new ArrayList<Arc>();
		DijkstraShortestPath dsp = new DijkstraShortestPath(graph.getVertexes(), graph.getArcs());
		//find shortest unique paths using Dijkstra algorithm
		for (Arc arc : graph.getArcs()) {
			//check that current arc is not a part of already existent paths
			if(arcsInPaths.contains(arc)) {
				continue;
			}
			//if such arc is viewed first time
			Path path = dsp.findShortestPath(graph.getDepo(), arc.getBeginNode());
			PathArc pathArc = path.new PathArc();
			pathArc.arc = arc;
			path.getPathArcs().add(pathArc);
			path.getPathArcs().addAll(dsp.findShortestPath(arc.getEndNode(), graph.getDepo()).getPathArcs());
			path.setCost(path.getPathArcs().size() * TRANSIT_COST);
			paths.add(path);
			//add all arcs into arcsInPaths
			for(PathArc pArc : path.getPathArcs()) {
				if(!arcsInPaths.contains(pArc.arc)) {
					arcsInPaths.add(pArc.arc);
				}
			}
		}
		//if number of paths is bigger than required
		if(paths.size() > nVehicles) {
			//find grater paths that contain all arcs from smaller one
			Collections.sort(paths, FeasibleSolution.pathComparator);
			for(int i = paths.size() - 1; i >= 0; i--) {
				Path currentPath = paths.get(i);
				//check bigger paths for similarity
				for(int j = i - 1; j >= 0; j--) {
					//arcs in prior path
					HashSet<Arc> arcsInPriorPath = new HashSet<Arc>();
					for(PathArc pathArc : paths.get(j).getPathArcs()){
						arcsInPriorPath.add(pathArc.arc);
					}
					//arcs in current path
					HashSet<Arc> arcsInCurrentPath = new HashSet<Arc>();
					for(PathArc pathArc : currentPath.getPathArcs()) {
						arcsInCurrentPath.add(pathArc.arc);
					}
					if (arcsInPriorPath.containsAll(arcsInCurrentPath)) {
						paths.remove(currentPath);
						break;
					}
				}
				//check stop condition
				if (paths.size() <= nVehicles) {
					break;
				}
			}
			//concatenate paths
			while(paths.size() > nVehicles) {
				Collections.sort(paths, FeasibleSolution.pathComparator);
				paths.get(nVehicles - 1).getPathArcs().addAll(paths.get(nVehicles).getPathArcs());
				paths.get(nVehicles - 1).setCost(paths.get(nVehicles - 1).getPathArcs().size() * TRANSIT_COST);
				paths.remove(nVehicles);
			}
		} else
			if (paths.size() < nVehicles) {
				//count number of appearances of arcs in paths
				int[] appearances = new int[arcsInPaths.size()];
				for(Path path : paths) {
					for(PathArc pathArc : path.getPathArcs()) {
						for(int i = 0; i < arcsInPaths.size(); i++) {
							if(pathArc.arc.equals(arcsInPaths.get(i))) {
								appearances[i]++;
								break;
							}
						}
					}
				}
				clonePaths:
				while(true) {
					Collections.sort(paths, FeasibleSolution.pathComparator);
					int pathsSize = paths.size();
					for(int pathIndex = 0; pathIndex < pathsSize; pathIndex++) {
						int count = 0;
						for(PathArc pathArc : paths.get(pathIndex).getPathArcs()) {
							for(int i = 0; i < arcsInPaths.size(); i++) {
								if(appearances[i] == 1 && pathArc.arc.equals(arcsInPaths.get(i))) {
									count++;
									if(count > 1) {
										paths.add(paths.get(pathIndex).copy());
									}
									//check stop option
									if(paths.size() == nVehicles) {
										break clonePaths;
									}
								}
							}
						}
					}
				}
			}
		//place servicing of arcs
		arcsInPaths = new ArrayList<Arc>();
		while (true) {
			Collections.sort(paths, FeasibleSolution.pathComparator);
			markArcReview:
			for(int i = paths.size() - 1; i >= 0; i--) {
				for(PathArc pathArc : paths.get(i).getPathArcs()) {
					if(!arcsInPaths.contains(pathArc.arc)) {
						pathArc.servicing = true;
						paths.get(i).setCost(paths.get(i).getCost() + SERVICE_COST - TRANSIT_COST);
						arcsInPaths.add(pathArc.arc);
						break markArcReview;
					}
				}
			}
			//stop when all arcs are serviced
			if(arcsInPaths.containsAll(graph.getArcs())) {
				break;
			}
		}
		//set deadlines
		for (Path path : paths) {
			long pathCost = 0;
			int deadline = 0;
			for (PathArc pathArc : path.getPathArcs()) {
				if (pathArc.servicing) {
					pathCost += SERVICE_COST;
					while (deadline < pathCost) {
						deadline += DEADLINE_STEP;
					}
					pathArc.arc.setDeadline(deadline);
				} else {
					pathCost += TRANSIT_COST;
				}
			}
		}
		//set generated paths to graph
		graph.getPathes().addAll(paths);
	}

	public static long getMaxCost() {
		maxCost = 0;
		for(Path path : graph.getPathes()) {
			if (maxCost < path.getCost()) {
				maxCost = path.getCost();
			}
		}
		return maxCost;
	}
}
