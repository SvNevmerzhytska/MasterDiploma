package edu.kpi.master.gui.helper;

import java.io.File;

import javax.swing.JDialog;

import edu.kpi.master.gui.GenerateGraphDialog;
import edu.kpi.master.gui.ViewGeneratedDataDetailsDialog;
import edu.kpi.master.gui.ViewInputDetailsDialog;
import edu.kpi.master.gui.ViewResultDetailsDialog;

public class Utils {
	public final static String xml = "xml";
	
	public final static GenerateGraphDialog generateGraphDialog = new GenerateGraphDialog();
	public final static ViewInputDetailsDialog viewInputDataDetails = new ViewInputDetailsDialog();
	public final static ViewResultDetailsDialog viewResultDetails = new ViewResultDetailsDialog();
	public final static ViewGeneratedDataDetailsDialog viewGeneratedDataDetails = new ViewGeneratedDataDetailsDialog();

    /*
     * Get the extension of a file.
     */  
    public static String getFileExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
    
    public static void showGenerateGraphDialog(){
    	generateGraphDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	generateGraphDialog.setVisible(true);
    }
    
    public static void hideGenerateGraphDialog(){
    	generateGraphDialog.dispose();
    }
    
    public static void showInputDetailsDialog(){
    	viewInputDataDetails.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	viewInputDataDetails.setVisible(true);
    }
    
    public static void hideInputDetailsDialog(){
    	viewInputDataDetails.dispose();
    }
    
    public static void showResultDetailsDialog(){
    	viewResultDetails.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	viewResultDetails.setVisible(true);
    }
    
    public static void hideResultDetailsDialog(){
    	viewResultDetails.dispose();
    }
    
    public static void showGeneratedDataDialog(){
    	viewGeneratedDataDetails.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	viewGeneratedDataDetails.setVisible(true);
    }
    
    public static void hideGeneratedDataDialog(){
    	viewGeneratedDataDetails.dispose();
    }
}
