package edu.kpi.master.datatypes;

import java.util.LinkedList;

public class Path {
	
	private LinkedList<PathArc> pathArcs = new LinkedList<PathArc>();
	private long cost;
	
	@Override
	public String toString() {
		String description = "Path = { cost:" + cost;
		for(PathArc pathArc : pathArcs){
			description = description + ", " + pathArc.arc.getBeginNode().getName() + "->"
					+ pathArc.arc.getEndNode().getName();
		}
		description = description + " }";
		return description;
	}

	public LinkedList<PathArc> getPathArcs() {
		return pathArcs;
	}

	public void setPathArcs(LinkedList<PathArc> pathArcs) {
		this.pathArcs = pathArcs;
	}

	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	public class PathArc {
		public Arc arc;
		public boolean servicing = false;
		
	}
}
