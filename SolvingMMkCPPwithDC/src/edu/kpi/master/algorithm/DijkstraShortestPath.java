package edu.kpi.master.algorithm;

import java.util.Collections;
import java.util.HashSet;

import edu.kpi.master.datatypes.Arc;
import edu.kpi.master.datatypes.Path;
import edu.kpi.master.datatypes.Vertex;

public class DijkstraShortestPath {
	
	private HashSet<VertexExtension> vertexes = new HashSet<VertexExtension>();
	private HashSet<Arc> arcs = new HashSet<Arc>();
	
	public DijkstraShortestPath(HashSet<Vertex> vertexes, HashSet<Arc> arcs) {
		for (Vertex vertex : vertexes) {
			this.vertexes.add(new VertexExtension(vertex));
		}
		this.arcs = arcs;
	}
	
	private void reinitialize() {
		for(VertexExtension v : vertexes) {
			v.distanse = Long.MAX_VALUE;
			v.mark = false;
			v.previousVertex = null;
		}
	}
	
	public void setArcs(HashSet<Arc> arcs) {
		this.arcs = arcs;
	}

	public Path findShortestPath(Vertex v1, Vertex v2) {
		//initialize start vertex
		reinitialize();
		VertexExtension currentVertex = null;
		for (VertexExtension vertexExt : vertexes) {
			if (vertexExt.vertex.equals(v1)) {
				vertexExt.distanse = 0;
				vertexExt.mark = true;
				currentVertex = vertexExt;
				break;
			}
		}
		//find end vertex
		VertexExtension endVertex = null;
		for (VertexExtension vertexExt : vertexes) {
			if(vertexExt.vertex.equals(v2)) {
				endVertex = vertexExt;
			}
		}
		//while not found shortest path from v1 to v2
		while(!endVertex.mark) {
			//update temporary distances
			for(VertexExtension vertexExt : vertexes) {
				for(Arc arc : arcs) {
					if(currentVertex.vertex.equals(arc.getBeginNode()) && 
							vertexExt.vertex.equals(arc.getEndNode())) {
						if(vertexExt.distanse > currentVertex.distanse + arc.getTransitCost()) {
							vertexExt.distanse = currentVertex.distanse + arc.getTransitCost();
							vertexExt.previousVertex = currentVertex.vertex;
							break;
						}
					}
				}
			}
			//find minimum temporary distance
			long minimumDist = Long.MAX_VALUE;
			for(VertexExtension vertExt : vertexes) {
				if(!vertExt.mark && vertExt.distanse < minimumDist) {
					minimumDist = vertExt.distanse;
					currentVertex = vertExt;
				}
			}
			currentVertex.mark = true;
		}
		//restore shortest path
		return restorePathForVertex(endVertex);
	}
	
	private Path restorePathForVertex (VertexExtension v) {
		Path path = new Path();
		path.setCost(v.distanse);
		//restoring vertex chain
		while(v.previousVertex != null) {
			Path.PathArc pathArc = path.new PathArc();
			pathArc.servicing = false;
			for(Arc arc : arcs) {
				if(arc.getBeginNode().equals(v.previousVertex) 
						&& arc.getEndNode().equals(v.vertex)) {
					pathArc.arc = arc;
					for(VertexExtension vExt : vertexes) {
						if(vExt.vertex.equals(v.previousVertex)) {
							v = vExt;
							break;
						}
					}
					break;
				}
			}
			path.getPathArcs().add(pathArc);
		}
		//reverse chain
		Collections.reverse(path.getPathArcs());
		return path;
	}
	
	public boolean isShortestPathExist(Vertex v1, Vertex v2) {
		//initialize start vertex
		reinitialize();
		VertexExtension currentVertex = null;
		for (VertexExtension vertexExt : vertexes) {
			if (vertexExt.vertex.equals(v1)) {
				vertexExt.distanse = 0;
				vertexExt.mark = true;
				currentVertex = vertexExt;
				break;
			}
		}
		//find end vertex
		VertexExtension endVertex = null;
		for (VertexExtension vertexExt : vertexes) {
			if(vertexExt.vertex.equals(v2)) {
				endVertex = vertexExt;
			}
		}
		//while not found shortest path from v1 to v2
		long time = System.currentTimeMillis();
		while(!endVertex.mark) {
			//update temporary distances
			for(VertexExtension vertexExt : vertexes) {
				for(Arc arc : arcs) {
					if(currentVertex.vertex.equals(arc.getBeginNode()) && 
							vertexExt.vertex.equals(arc.getEndNode())) {
						if(vertexExt.distanse > currentVertex.distanse + arc.getTransitCost()) {
							vertexExt.distanse = currentVertex.distanse + arc.getTransitCost();
							vertexExt.previousVertex = currentVertex.vertex;
							break;
						}
					}
				}
			}
			//find minimum temporary distance
			long minimumDist = Long.MAX_VALUE;
			for(VertexExtension vertExt : vertexes) {
				if(!vertExt.mark && vertExt.distanse < minimumDist) {
					minimumDist = vertExt.distanse;
					currentVertex = vertExt;
				}
			}
			currentVertex.mark = true;
			//if computation is too long, that algorithm can not reach v2
			if(System.currentTimeMillis() - time > arcs.size() * 10) {
				return false;
			}
		}
		//restore shortest path
		return true;
	}
	
	//helper class for Dijkstra algorithm
	private class VertexExtension {
		Vertex vertex;
		long distanse;
		boolean mark;
		Vertex previousVertex;
		
		public VertexExtension(Vertex vertex) {
			this.vertex = vertex;
			this.distanse = Long.MAX_VALUE;
			this.mark = false;
		}
	}
}
