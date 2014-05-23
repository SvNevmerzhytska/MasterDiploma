package edu.kpi.master.datatypes;

import java.util.Set;

public class Vertex {
	
	private String name;
	private Set<Arc> incidentArcs;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Arc> getIncidentArcs() {
		return incidentArcs;
	}
	public void setIncidentArcs(Set<Arc> incidentArcs) {
		this.incidentArcs = incidentArcs;
	}

}
