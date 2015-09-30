package com.shakhin.logger.service.handler.Impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.shakhin.logger.data.Formate;
import com.shakhin.logger.data.LogRecord;
import com.shakhin.logger.manager.ObjectFactory;
import com.shakhin.logger.service.formater.Formater;
import com.shakhin.logger.service.handler.Handler;

public class FileHandler extends Handler {
	
	private  String fileName=null;
	private  Formater formater=null;
	private  BufferedWriter writer=null;

	@Override
	public void publish(LogRecord record) {

		String message=formater.getTagMessage(record);
		
		try{
		printF(message);
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public String getFileName() {
		
		return fileName;
	}

	public void setFileName(String fileName) {
		
		this.fileName = fileName;
	}

	public void setFormater(Formate formate) {
		
		switch(formate) {
			case TXT : this.formater=ObjectFactory.getTXTFormater();
			case XML : this.formater=ObjectFactory.getXMLFormater();
			case HTML : this.formater=ObjectFactory.getHTMLFormater();
			default:this.formater=ObjectFactory.getTXTFormater();
		}
	}
	
	public void init() throws IOException{
		
		writer = new BufferedWriter(new FileWriter(new File(fileName),true));	
		writer.write(formater.getHeader());
		writer.newLine();
	} 
	
	private void close() throws IOException{
		
		writer.write(formater.getFooter());
		writer.newLine();
		writer.close();
	}
	
	private void printF(String message) throws IOException{
		
		writer.write(message);
		writer.newLine();
		writer.flush();
	}
	
	protected void finalize() throws Throwable {
		
	     try {
	         close();       
	     } finally {
	         super.finalize();
	     }
	 }

}