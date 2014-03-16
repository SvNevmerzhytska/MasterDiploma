package edu.kpi.master.datatypes;

import java.util.LinkedList;

public class Path {
	
	private int pathNumber;
	private LinkedList<Arc> arcs;

	public int getPathNumber() {
		return pathNumber;
	}

	public void setPathNumber(int pathNumber) {
		this.pathNumber = pathNumber;
	}

	public LinkedList<Arc> getArcs() {
		return arcs;
	}

	public void setArcs(LinkedList<Arc> arcs) {
		this.arcs = arcs;
	}

}
