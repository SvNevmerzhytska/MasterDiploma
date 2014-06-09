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
import edu.kpi.master.datatypes.Path;
import edu.kpi.master.datatypes.Path.PathArc;

public class Writer {

	public static void writeDataToFile(File xmlFile){
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();  
			
			//write paths to file content
			writePaths(doc);
			
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
	
	private static void writePaths(Document doc){
		Element rootElement = doc.createElement("pathSet");
		doc.appendChild(rootElement);
		rootElement.setAttribute("maxCost", Long.toString(FeasibleSolution.maxCost));
		rootElement.setAttribute("time", Long.toString(FeasibleSolution.computationTime));
		rootElement.setAttribute("expectedCost", Long.toString(PresetSolution.maxPathCost));
		//write all paths
		for(Path path : FeasibleSolution.graph.getPathes()) {
			Element pathElement = doc.createElement("path");
			rootElement.appendChild(pathElement);
			pathElement.setAttribute("cost", Long.toString(path.getCost()));
			//write chain of arcs
			for(PathArc pathArc : path.getPathArcs()) {
				Element arc = doc.createElement("arc");
				pathElement.appendChild(arc);
				arc.setAttribute("begin", pathArc.arc.getBeginNode().getName());
				arc.setAttribute("end", pathArc.arc.getEndNode().getName());
				arc.setAttribute("service", Boolean.toString(pathArc.servicing));
			}
		}
	}
	
}
