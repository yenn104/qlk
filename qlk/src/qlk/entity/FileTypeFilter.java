package qlk.entity;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileTypeFilter extends FileFilter{
	private final String extendsion;
	private final String description;
	
	
	public FileTypeFilter(String extendsion, String description) {
		this.extendsion = extendsion;
		this.description = description;
	}


	@Override
	public boolean accept(File file) {
		if(file.isDirectory()) {
			return true;
		}
		return file.getName().endsWith(extendsion);
	}


	@Override
	public String getDescription() {
		return description + String.format("(*%s)", extendsion);
	}



}