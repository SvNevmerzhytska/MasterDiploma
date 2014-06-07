package edu.kpi.master.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.kpi.master.algorithm.PresetSolution;
import edu.kpi.master.datatypes.Arc;
import edu.kpi.master.datatypes.Graph;
import edu.kpi.master.datatypes.Path;
import edu.kpi.master.datatypes.Vertex;

public class Reader {

	public static void readDataFromFile(File xmlFile){
		try {
			
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);  
			
			doc.getDocumentElement().normalize();
			
			PresetSolution.graph = new Graph();
			
			//read vertexes from file
			readVertexes(doc);
			
			//read arcs from file
			readArcs(doc);
			
			//read paths from file
			readPaths(doc);
			
		} catch (Exception e) {  
			e.printStackTrace();  
		}
	}
	
	public static void readVertexes(Document doc){
		NodeList nodeList = doc.getElementsByTagName("vertex");
		List<String> names = new ArrayList<String>();
		for (int temp = 0; temp < nodeList.getLength(); temp++) {  
			Node node = nodeList.item(temp);  
		    if (node.getNodeType() == Node.ELEMENT_NODE) {
		    	Element vertexElement = (Element) node;
		    	String name = vertexElement.getAttribute("name");
		    	Vertex vertex = new Vertex(name);
		    	PresetSolution.graph.getVertexes().add(vertex);
		    	names.add(name);
		    }
		}
		//find the vertex with the smallest name and set it as depot
		Collections.sort(names);
		for(Vertex vertex : PresetSolution.graph.getVertexes()){
			if (vertex.getName().equals(names.get(0))) {
				PresetSolution.graph.setDepo(vertex);
				break;
			}
		}
	}
	
	public static void readArcs(Document doc){
		NodeList nodeList = doc.getElementsByTagName("arc");
		for (int temp = 0; temp < nodeList.getLength(); temp++) {  
			Node node = nodeList.item(temp);  
		    if (node.getNodeType() == Node.ELEMENT_NODE) {
		    	Element arcElement = (Element) node;  
		    	Arc arc = new Arc();
		    	Vertex beginVertex = new Vertex(arcElement.getAttribute("begin"));
		    	Vertex endVertex = new Vertex(arcElement.getAttribute("end"));
		    	for(Vertex vertex : PresetSolution.graph.getVertexes()){
		    		if(vertex.equals(beginVertex)) {
		    			arc.setBeginNode(vertex);
		    			if(arc.getEndNode() != null) {
		    				break;
		    			}
		    		} else if(vertex.equals(endVertex)){
		    			arc.setEndNode(vertex);
		    			if(arc.getBeginNode() != null) {
		    				break;
		    			}
		    		}
		    	}
		    	arc.setServiceCost(Integer.parseInt(arcElement.getAttribute("serviceCost")));
		    	arc.setTransitCost(Integer.parseInt(arcElement.getAttribute("transitCost")));
		    	arc.setDeadline(Integer.parseInt(arcElement.getAttribute("deadline")));
		    	PresetSolution.graph.getArcs().add(arc);
		    }
		}
	}
	
	public static void readPaths(Document doc){
		NodeList nodeList = doc.getElementsByTagName("path");
		for (int temp = 0; temp < nodeList.getLength(); temp++) {  
			Node node = nodeList.item(temp);  
		    if (node.getNodeType() == Node.ELEMENT_NODE) {
		    	Element pathElement = (Element) node;
		    	Path path = new Path();
		    	path.setCost(Long.parseLong(pathElement.getAttribute("cost")));
		    	NodeList arcEntries = pathElement.getElementsByTagName("arcEntry");
		    	for(int i = 0; i < arcEntries.getLength(); i++) {
		    		Path.PathArc arcEntry = path.new PathArc();
		    		//find existent arc to add into path
		    		Arc testArc = new Arc();
	    			testArc.setBeginNode(new Vertex(((Element) arcEntries.item(i)).getAttribute("begin")));
	    			testArc.setEndNode(new Vertex(((Element) arcEntries.item(i)).getAttribute("end")));
		    		for(Arc arc : PresetSolution.graph.getArcs()){
		    			if (arc.equals(testArc)) {
		    				arcEntry.arc = arc;
		    				break;
		    			}
		    		}
		    		//
		    		arcEntry.servicing = Boolean.parseBoolean(((Element) arcEntries.item(i)).getAttribute("service"));
		    		path.getPathArcs().add(arcEntry);
		    	}
		    	PresetSolution.graph.getPathes().add(path);
		    	//update max cost of path
		    	if (path.getCost() > PresetSolution.maxPathCost) {
		    		PresetSolution.maxPathCost = path.getCost();
		    	}
		    }
		}
		PresetSolution.graph.setNVehicles(PresetSolution.graph.getPathes().size());
	}
}
