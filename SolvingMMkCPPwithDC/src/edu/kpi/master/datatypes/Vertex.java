package edu.kpi.master.datatypes;

import java.util.HashSet;
import java.util.Set;

public class Vertex {
	
	private String name;
	//arcs ended in current vertex
	private Set<Arc> inArcs = new HashSet<Arc>();
	//arcs started in current vertex
	private Set<Arc> outArcs = new HashSet<Arc>();
	
	public Vertex(String name){
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(! (obj instanceof Vertex)){
			return false;
		}
		if(this.name.equals(((Vertex) obj).name)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public String toString() {
		return "Vertex = { " + name + " }";
	};
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Arc> getInArcs() {
		return inArcs;
	}
	public void setInArcs(Set<Arc> inArcs) {
		this.inArcs = inArcs;
	}
	public Set<Arc> getOutArcs() {
		return outArcs;
	}
	public void setOutArcs(Set<Arc> outArcs) {
		this.outArcs = outArcs;
	}

}
