package edu.kpi.master.io;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.kpi.master.algorithm.FeasibleSolution;
import edu.kpi.master.algorithm.PresetSolution;
import edu.kpi.master.datatypes.Arc;
import edu.kpi.master.datatypes.Path;
import edu.kpi.master.datatypes.Path.PathArc;
import edu.kpi.master.datatypes.Vertex;
import edu.kpi.master.generator.Generator;

public class Writer {

	public static void writeDataToFile(File xmlFile, boolean isResult){
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();  
			
			//write data to file content
			if (isResult) {
				writeResult(doc);
			} else {
				writeGenerateData(doc);
			}
			
			// write the content into xml file
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xmlFile);
			transformer.transform(source, result);
			System.out.println("File saved!\n");
			
		} catch (Exception e) {  
			e.printStackTrace();  
		}
	}
	
	private static void writeResult(Document doc){
		Element rootElement = doc.createElement("result");
		doc.appendChild(rootElement);
		rootElement.setAttribute("nPaths", Long.toString(FeasibleSolution.graph.getNVehicles()));
		rootElement.setAttribute("nVertexes", Long.toString(FeasibleSolution.graph.getVertexes().size()));
		rootElement.setAttribute("nArcs", Long.toString(PresetSolution.graph.getArcs().size()));
		rootElement.setAttribute("expectedCost", Long.toString(PresetSolution.maxPathCost));
		rootElement.setAttribute("maxCost", Long.toString(FeasibleSolution.maxCost));
		rootElement.setAttribute("time", Long.toString(FeasibleSolution.computationTime));
		Element pathSet = doc.createElement("pathSet");
		rootElement.appendChild(pathSet);
		pathSet.setAttribute("size", Integer.toString(FeasibleSolution.graph.getPathes().size()));
		//write all paths
		for(Path path : FeasibleSolution.graph.getPathes()) {
			Element pathElement = doc.createElement("path");
			pathSet.appendChild(pathElement);
			pathElement.setAttribute("cost", Long.toString(path.getCost()));
			//write chain of arcs
			for(PathArc pathArc : path.getPathArcs()) {
				Element arc = doc.createElement("arcEntry");
				pathElement.appendChild(arc);
				arc.setAttribute("begin", pathArc.arc.getBeginNode().getName());
				arc.setAttribute("end", pathArc.arc.getEndNode().getName());
				arc.setAttribute("service", Boolean.toString(pathArc.servicing));
			}
		}
	}
	
	private static void writeGenerateData(Document doc){
		Element rootElement = doc.createElement("graph");
		doc.appendChild(rootElement);
		writeGeneratedVertexes(doc, rootElement);
		writeGeneratedArcs(doc, rootElement);
		writeGeneratedPaths(doc, rootElement);
	}
	
	private static void writeGeneratedVertexes(Document doc, Element rootElement) {
		Element vertexSetElement = doc.createElement("vertexSet");
		rootElement.appendChild(vertexSetElement);
		//write all paths
		for(Vertex vertex : Generator.graph.getVertexes()) {
			Element vertexElement = doc.createElement("vertex");
			vertexSetElement.appendChild(vertexElement);
			vertexElement.setAttribute("name", vertex.getName());
		}
	}
	
	private static void writeGeneratedArcs(Document doc, Element rootElement) {
		Element arcSetElement = doc.createElement("arcSet");
		rootElement.appendChild(arcSetElement);
		//write all paths
		for(Arc arc : Generator.graph.getArcs()) {
			Element arcElement = doc.createElement("arc");
			arcSetElement.appendChild(arcElement);
			arcElement.setAttribute("begin", arc.getBeginNode().getName());
			arcElement.setAttribute("end", arc.getEndNode().getName());
			arcElement.setAttribute("serviceCost", Integer.toString(arc.getServiceCost()));
			arcElement.setAttribute("transitCost", Integer.toString(arc.getTransitCost()));
			arcElement.setAttribute("deadline", Integer.toString(arc.getDeadline()));
		}
	}
	
	private static void writeGeneratedPaths(Document doc, Element rootElement) {
		Element pathSetElement = doc.createElement("pathSet");
		rootElement.appendChild(pathSetElement);
		//write all paths
		for(Path path : Generator.graph.getPathes()) {
			Element pathElement = doc.createElement("path");
			pathSetElement.appendChild(pathElement);
			pathElement.setAttribute("cost", Long.toString(path.getCost()));
			//write chain of arcs
			for(PathArc pathArc : path.getPathArcs()) {
				Element arc = doc.createElement("arcEntry");
				pathElement.appendChild(arc);
				arc.setAttribute("begin", pathArc.arc.getBeginNode().getName());
				arc.setAttribute("end", pathArc.arc.getEndNode().getName());
				arc.setAttribute("service", Boolean.toString(pathArc.servicing));
			}
		}
	}
}
