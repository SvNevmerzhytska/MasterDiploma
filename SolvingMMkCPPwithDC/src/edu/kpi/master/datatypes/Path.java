package edu.kpi.master.datatypes;

import java.util.LinkedList;

public class Path {
	
	private LinkedList<PathArc> pathArcs = new LinkedList<PathArc>();
	private long cost;
	
	public Path copy() {
		Path result = new Path();
		result.cost = this.cost;
		result.pathArcs = new  LinkedList<>(this.pathArcs);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(! (obj instanceof Path)){
			return false;
		}
		Path path = (Path) obj;
		if(this.pathArcs.size() != path.pathArcs.size()) {
			return false;
		}
		for(int i = 0; i < this.pathArcs.size(); i++) {
			if(!(this.pathArcs.get(i).arc.equals(path.pathArcs.get(i).arc) 
					&& this.pathArcs.get(i).servicing == path.pathArcs.get(i).servicing)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Path = { cost:" + cost + " chain: " + getArcChain() + " }";
	}
	
	public String getArcChain(){
		String result = "";
		for(PathArc pathArc : pathArcs) {
			if (!result.isEmpty()) {
				result = result + ", ";
			}
			result = result + pathArc.arc.getName();
			if(pathArc.servicing) {
				result = result + "(s)";
			}
		}
		return result;
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
