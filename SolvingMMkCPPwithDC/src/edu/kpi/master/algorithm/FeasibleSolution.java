package edu.kpi.master.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import edu.kpi.master.datatypes.Arc;
import edu.kpi.master.datatypes.Graph;
import edu.kpi.master.datatypes.Path;
import edu.kpi.master.datatypes.Path.PathArc;
import edu.kpi.master.datatypes.Vertex;
import edu.kpi.master.gui.helper.Utils;

public class FeasibleSolution {

	public static Graph graph = new Graph();
	public static long computationTime;
	public static long maxCost;
	
	private static boolean possibleToContinue = true;
	private static Set<Path> simpleRouts;
	
	private static Comparator<Path> pathComparator = new Comparator<Path>() {
		@Override
		public int compare(Path arg0, Path arg1) {
			return (int) (arg1.getCost() - arg0.getCost());
		}
	};
	
	public static void findFeasibleSolution(){
		graph = new Graph();
		possibleToContinue = true;
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
		if(simpleRouts.size() <= graph.getNVehicles()) {
			System.out.println("Feasible solution was found on step 1.");
			graph.setPathes(simpleRouts);
			possibleToContinue = false;
			return;
		}
		System.out.println();
	}
	
	//step 2
	public static void step2() {
		System.out.println("Step 2. Review simple routes...");
		Set<Arc> arcsToView = graph.getArcs();
		while (!arcsToView.isEmpty()) {
			int minReserve = Integer.MAX_VALUE;
			Arc currentArc = null;
			Path currentPath = null;
			//find arc with minimum reserve
			for(Arc arc : arcsToView)  {
				if (arc.getReserve() > 0 && arc.getReserve() < minReserve) {
					minReserve = arc.getReserve();
					currentArc = arc;
				}
			}
			if(currentArc == null) {
				break;
			}
			//find simple rout (if it is exist)
			markFindSimple:
			for(Path path : simpleRouts) {
				for(PathArc pathArc : path.getPathArcs()) {
					if(pathArc.servicing && pathArc.arc.equals(currentArc)) {
						currentPath = path;
						break markFindSimple;
					}
				}
			}
			if(currentPath != null) {
				graph.getPathes().add(currentPath);
				simpleRouts.remove(currentPath);
			} else {
				//find through not simple routes
				markFind:
				for(Path path : graph.getPathes()) {
					for(PathArc pathArc : path.getPathArcs()) {
						if(pathArc.servicing && pathArc.arc.equals(currentArc)) {
							currentPath = path;
							break markFind;
						}
					}
				}
			}
			if(currentPath == null) {
				System.out.println(currentArc);
			}
			//try to add transit arcs prior to current as serviced in current path
			int index = 0;
			for(PathArc pathArc : currentPath.getPathArcs()) {
				if (pathArc.arc.equals(currentArc)) {
					index = currentPath.getPathArcs().indexOf(pathArc);
					break;
				}
			}
			while(--index >= 0) {
				PathArc tempArcPath = currentPath.getPathArcs().get(index);
				//check that this arc is not serviced
				boolean isServesed = false;
				markCheckServicing:
				for(Path path : graph.getPathes()) {
					for(PathArc pathArc : path.getPathArcs()) {
						if (pathArc.servicing && pathArc.arc.equals(tempArcPath.arc)) {
							isServesed = true;
							break markCheckServicing;
						}
					}
				}
				//try to add arc as serviced
				if(!isServesed) {
					int difference = tempArcPath.arc.getServiceCost() - tempArcPath.arc.getTransitCost();
					//check that adding will be feasible
					boolean isAllowed = true;
					for(int i = index + 1; i < currentPath.getPathArcs().size(); i++) {
						if(currentPath.getPathArcs().get(i).servicing 
								&& difference > currentPath.getPathArcs().get(i).arc.getReserve()) {
							isAllowed = false;
							break;
						}
					}
					//add arc as serviced
					if(isAllowed) {
						tempArcPath.servicing = true;
						int serviceTime = 0;
						//update service time (reserve updates automatically)
						for(PathArc pathArc : currentPath.getPathArcs()) {
							if(pathArc.servicing) {
								serviceTime = serviceTime + pathArc.arc.getServiceCost();
								pathArc.arc.setServiceTime(serviceTime);
							} else {
								serviceTime = serviceTime + pathArc.arc.getTransitCost();
							}
						}
						currentPath.setCost(serviceTime);
						//remove simple path
						markRemove:
						for(Path simplePath : simpleRouts) {
							for(PathArc pathArc : simplePath.getPathArcs()) {
								if(pathArc.servicing && pathArc.arc.equals(tempArcPath.arc)) {
									simpleRouts.remove(simplePath);
									break markRemove;
								}
							}
						}
					}
				}
			}
			arcsToView.remove(currentArc);
			//check stop condition
			if (graph.getPathes().size() + simpleRouts.size() <= graph.getNVehicles()) {
				System.out.println("Feasible solution was found on step 2.");
				possibleToContinue = false;
				break;
			}
		}
		if(!simpleRouts.isEmpty()) {
			graph.getPathes().addAll(simpleRouts);
			simpleRouts = null;
		}
		for(Path path : graph.getPathes()) {
			System.out.println(path);
		}
		System.out.println();
	}
	
	//step 3
	public static void step3() {
		System.out.println("Step 3. Merge identical routes...");
		//sort routes by its cost
		ArrayList<Path> sortedPathes = new ArrayList<Path>(graph.getPathes());
		Collections.sort(sortedPathes, pathComparator);
		for(int i = sortedPathes.size() - 1; i >= 0; i--) {
			Path currentPath = sortedPathes.get(i);
			Path priorPath = null;
			//check bigger paths for similarity
			for(int j = i - 1; j >= 0; j--) {
				//arcs in prior path
				HashSet<Arc> arcsInPriorPath = new HashSet<Arc>();
				for(PathArc pathArc : sortedPathes.get(j).getPathArcs()){
					arcsInPriorPath.add(pathArc.arc);
				}
				//arcs in current path
				HashSet<Arc> arcsInCurrentPath = new HashSet<Arc>();
				for(PathArc pathArc : currentPath.getPathArcs()) {
					arcsInCurrentPath.add(pathArc.arc);
				}
				if (arcsInPriorPath.containsAll(arcsInCurrentPath)) {
					priorPath = sortedPathes.get(j);
					break;
				}
			}
			//update servicing marks in prior path
			if (priorPath != null) {
				for(PathArc pathArc : currentPath.getPathArcs()) {
					if (pathArc.servicing) {
						for (PathArc pathArc2 : priorPath.getPathArcs()) {
							if (pathArc2.arc.equals(pathArc.arc)) {
								pathArc2.servicing = true;
								break;
							}
						}
					}
				}
				//update parameters in prior path
				int serviceTime = 0;
				boolean needToRestore = false;
				//update service time (reserve updates automatically)
				for(PathArc pathArc : priorPath.getPathArcs()) {
					if(pathArc.servicing) {
						serviceTime = serviceTime + pathArc.arc.getServiceCost();
						if (serviceTime > pathArc.arc.getDeadline()) {
							needToRestore = true;
							break;
						} else {
							pathArc.arc.setServiceTime(serviceTime);
						}
					} else {
						serviceTime = serviceTime + pathArc.arc.getTransitCost();
					}
				}
				//if cannot merge routes
				if(needToRestore) {
					for(PathArc pathArc : currentPath.getPathArcs()) {
						if (pathArc.servicing) {
							for (PathArc pathArc2 : priorPath.getPathArcs()) {
								if (pathArc2.arc.equals(pathArc.arc)) {
									pathArc2.servicing = false;
									break;
								}
							}
						}
					}
					serviceTime = 0;
					//update service time (reserve updates automatically)
					for(PathArc pathArc : priorPath.getPathArcs()) {
						if(pathArc.servicing) {
							serviceTime = serviceTime + pathArc.arc.getServiceCost();
							pathArc.arc.setServiceTime(serviceTime);
						} else {
							serviceTime = serviceTime + pathArc.arc.getTransitCost();
						}
					}
				} else {
					//delete current path
					sortedPathes.remove(currentPath);
				}
				priorPath.setCost(serviceTime);
			}
			//sort paths
			Collections.sort(sortedPathes, pathComparator);
			//check stop condition
			if (sortedPathes.size() <= graph.getNVehicles()) {
				System.out.println("Feasible solution was found on step 3.");
				possibleToContinue = false;
				break;
			}
		}
		//save sorted routes
		graph.setPathes(new HashSet<>(sortedPathes));
		for(Path path : graph.getPathes()) {
			System.out.println(path);
		}
		System.out.println();
	}
	
	//step 4
	public static void step4() {
		
	}
}
