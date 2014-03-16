package edu.kpi.master.datatypes;

import java.util.Set;

public class Graph {
	
	private Set<Arc> arcs;
	private Set<Vertex> vertexes;
	private Vertex depo;
	private Set<Path> pathes;
	
	public Set<Arc> getArcs() {
		return arcs;
	}
	public void setArcs(Set<Arc> arcs) {
		this.arcs = arcs;
	}
	public Set<Vertex> getVertexes() {
		return vertexes;
	}
	public void setVertexes(Set<Vertex> vertexes) {
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
	
}
