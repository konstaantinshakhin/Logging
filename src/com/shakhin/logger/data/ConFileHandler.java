package com.shakhin.logger.data;

public class ConFileHandler extends ConfHandler{
	
	private String fileName;
	private  Formate formate;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Formate getFormate() {
		return formate;
	}
	public void setFormate(Formate formate) {
		this.formate = formate;
	}
	
	public String toString(){
		return super.toString()+"\n"+name+"<>"+fileName+"<>"+formate+"\n";
	}
	
	
}
