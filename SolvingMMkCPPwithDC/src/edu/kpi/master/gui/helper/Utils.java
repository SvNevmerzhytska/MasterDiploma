package edu.kpi.master.gui.helper;

import java.io.File;

import javax.swing.JDialog;

import edu.kpi.master.gui.GenerateGraphDialog;

public class Utils {
	public final static String xml = "xml";
	
	public final static GenerateGraphDialog generateGraphDialog = new GenerateGraphDialog();

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
}
