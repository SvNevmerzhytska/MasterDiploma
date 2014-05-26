package edu.kpi.master.gui.helper;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class XmlFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
	        return true;
	    }

	    String extension = Utils.getFileExtension(f);
	    if (extension != null) {
	        if (extension.equals(Utils.xml)) {
	                return true;
	        } else {
	            return false;
	        }
	    }

		return false;
	}

	@Override
	public String getDescription() {
		
		return "XML files";
	}

}
