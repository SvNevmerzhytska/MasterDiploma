package edu.kpi.master.datatypes;

import java.util.HashSet;
import java.util.Set;

public class Graph {
	
	private HashSet<Arc> arcs = new HashSet<Arc>();
	private HashSet<Vertex> vertexes = new HashSet<Vertex>();
	private Vertex depo;
	private Set<Path> pathes = new HashSet<Path>();
	private int nVehicles;
	
	public HashSet<Arc> getArcs() {
		return arcs;
	}
	public void setArcs(HashSet<Arc> arcs) {
		this.arcs = arcs;
	}
	public HashSet<Vertex> getVertexes() {
		return vertexes;
	}
	public void setVertexes(HashSet<Vertex> vertexes) {
		this.vertexes = vertexes;
	}
	public Vertex getDepo() {
		return depo;
	}
	public void setDepo(Vertex depo) {
		this.depo = depo;
	}
	public Set<Path> getPathes() {
		return pathes;
	}
	public void setPathes(Set<Path> pathes) {
		this.pathes = pathes;
	}
	public int getNVehicles() {
		return nVehicles;
	}
	public void setNVehicles(int nVehicles) {
		this.nVehicles = nVehicles;
	}
	
}
