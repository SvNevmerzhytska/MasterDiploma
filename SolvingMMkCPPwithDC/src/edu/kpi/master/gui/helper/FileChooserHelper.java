package edu.kpi.master.gui.helper;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;

import edu.kpi.master.algorithm.Generator;
import edu.kpi.master.io.Reader;
import edu.kpi.master.io.Writer;

public class FileChooserHelper {
	final static JFileChooser fc = new JFileChooser();
	private static String fileName = "";
	
	public static void openFile(Component comp){
		//set only xml filter
		fc.setFileFilter(new XmlFileFilter());
		fc.setAcceptAllFileFilterUsed(false);
		//set project folder as current directory
		fc.setCurrentDirectory(new File(System.getProperty("user.dir") + "/resources/input"));
		int returnVal = fc.showOpenDialog(comp);
		//action on Open
		if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            fileName = file.getName().replace(".xml", "");
            Reader.readDataFromFile(file);
        }
	}
	
	public static void saveFile(Component comp){
		//set only xml filter
		fc.setFileFilter(new XmlFileFilter());
		fc.setAcceptAllFileFilterUsed(false);
		//form name of file
		String fileName = "test_p" + Generator.nVehicles + "v" + Generator.nVertexes + "a" + Generator.nArcs + "d" + Generator.DEADLINE_RESERVE;
		DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssSS");
		Date date = new Date();
		File f;
		try {
			f = new File(new File(fileName + "_" + dateFormat.format(date) + ".xml").getCanonicalPath());
			fc.setSelectedFile(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//set project folder as current directory
		fc.setCurrentDirectory(new File(System.getProperty("user.dir") + "/resources/input"));
		int returnVal = fc.showSaveDialog(comp);
		//action on Save
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			fileName = file.getName().replace(".xml", "");
			Writer.writeDataToFile(file, false);
        }
	}
	
	public static void saveResultFile(Component comp){
		//set only xml filter
		fc.setFileFilter(new XmlFileFilter());
		fc.setAcceptAllFileFilterUsed(false);
		//
		File folder = new File(System.getProperty("user.dir") + "/resources/output");
		int maxCount = 0;
		for (File file : folder.listFiles()) {
			if (file.isFile() && file.getName().startsWith(fileName) && file.getName().endsWith(".xml")) {
				try {
					int x = Integer.parseInt(file.getName().replace(fileName + "_", "").replace(".xml", ""));
					if (x >= maxCount) {
						maxCount++;
					}
				}
				catch (NumberFormatException ex) {
					ex.printStackTrace();
				}
			} 
		}
		//form name of file
		File f;
		try {
			f = new File(new File(fileName + "_" + maxCount + ".xml").getCanonicalPath());
			fc.setSelectedFile(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//set project folder as current directory
		fc.setCurrentDirectory(folder);
		int returnVal = fc.showSaveDialog(comp);
		//action on Save
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			Writer.writeDataToFile(file, true);
        }
	}
}
