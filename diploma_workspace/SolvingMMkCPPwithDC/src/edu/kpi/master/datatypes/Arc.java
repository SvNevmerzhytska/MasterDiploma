package edu.kpi.master.datatypes;

public class Arc {
	
	private int serviceCost;
	private int transitCost;
	private int demand;
	private boolean includeToRoute;
	private int priority;
	
	
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
	public boolean isIncludeToRoute() {
		return includeToRoute;
	}
	public void setIncludeToRoute(boolean includeToRoute) {
		this.includeToRoute = includeToRoute;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	

}
