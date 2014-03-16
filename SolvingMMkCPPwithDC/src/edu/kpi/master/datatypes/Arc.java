package edu.kpi.master.datatypes;

public class Arc {
	
	private Vertex beginNode;
	private Vertex endNode;
	private int serviceCost;
	private int transitCost;
	private int demand;
	private int priority;
	private int deadline;
	private int serviceTime;
	
	
	public Vertex getBeginNode() {
		return beginNode;
	}
	public void setBeginNode(Vertex beginNode) {
		this.beginNode = beginNode;
	}
	public Vertex getEndNode() {
		return endNode;
	}
	public void setEndNode(Vertex endNode) {
		this.endNode = endNode;
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
	public int getDemand() {
		return demand;
	}
	public void setDemand(int demand) {
		this.demand = demand;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
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
	

}
