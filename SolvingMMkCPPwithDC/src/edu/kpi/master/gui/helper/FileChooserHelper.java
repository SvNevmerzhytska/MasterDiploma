package edu.kpi.master.gui.helper;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFileChooser;

import edu.kpi.master.io.Reader;

public class FileChooserHelper {
	final static JFileChooser fc = new JFileChooser();
	
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
            Reader.readDataFromFile(file);
        }
	}
	
	public static void saveFile(Component comp){
		//set only xml filter
		fc.setFileFilter(new XmlFileFilter());
		fc.setAcceptAllFileFilterUsed(false);
		//form name of file
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		File f;
		try {
			f = new File(new File(dateFormat.format(date) + ".xml").getCanonicalPath());
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
        }
	}
	
	public static void saveResultFile(Component comp){
		//set only xml filter
		fc.setFileFilter(new XmlFileFilter());
		fc.setAcceptAllFileFilterUsed(false);
		//form name of file
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		File f;
		try {
			f = new File(new File(dateFormat.format(date) + ".xml").getCanonicalPath());
			fc.setSelectedFile(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//set project folder as current directory
		fc.setCurrentDirectory(new File(System.getProperty("user.dir") + "/resources/output"));
		int returnVal = fc.showSaveDialog(comp);
		//action on Save
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
        }
	}
}
