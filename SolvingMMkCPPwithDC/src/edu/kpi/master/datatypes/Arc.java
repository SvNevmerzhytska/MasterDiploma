package edu.kpi.master.datatypes;

public class Arc {
	
	private Vertex beginNode;
	private Vertex endNode;
	private int serviceCost;
	private int transitCost;
	private int deadline;
	//time to traverse and to service the arc
	private int serviceTime;
	//deadline - serviceTime
	private int reserve;
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(! (obj instanceof Arc)){
			return false;
		}
		if(this.beginNode.equals(((Arc) obj).beginNode)
				&& this.endNode.equals(((Arc) obj).endNode)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Arc = { " + beginNode.getName() + "->" + endNode.getName()
				+ ", serviceCost:" + serviceCost + ", transitCost:" + transitCost
				+ ", deadline:" + deadline + " }";
	}
	
	public Vertex getBeginNode() {
		return beginNode;
	}
	public void setBeginNode(Vertex beginNode) {
		this.beginNode = beginNode;
		beginNode.getOutArcs().add(this);
		beginNode.getIncidentArcs().add(this);
	}
	public Vertex getEndNode() {
		return endNode;
	}
	public void setEndNode(Vertex endNode) {
		this.endNode = endNode;
		endNode.getInArcs().add(this);
		endNode.getIncidentArcs().add(this);
	}
	public int getServiceCost() {
		return serviceCost;
	}
	public void setServiceCost(int serviceCost) {
		this.serviceCost = serviceCost;
	}
	public int getTransitCost() {
		return transitCost;
	}
	public void setTransitCost(int transitCost) {
		this.transitCost = transitCost;
	}
	public int getDeadline() {
		return deadline;
	}
	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}
	public int getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	public int getReserve() {
		return reserve;
	}
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	

}
