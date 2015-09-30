package com.shakhin.logger.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogRecord {	
	
	private String logName="root";
	private Level level;	
	private String message;
	private Exception e;
	private Object obj;
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	

	public Exception getException() {
		return e;
	}

	public void setException(Exception e) {
		this.e = e;
	}
	
	public String toString(){
		if ((e==null)&(obj==null))  return getTime()+":"+logName+":"+level+":"+message+"\n";
		if ((e!=null)&(obj==null))  return getTime()+":"+logName+":"+level+":"+message+":"+e+"\n";
		if ((e==null)&(obj!=null))  return getTime()+":"+logName+":"+level+":"+message+":"+obj+"\n";
		return getTime();
	}
	
	public String getTime(){
		   Date date = new Date();
		   return dateFormat.format(date);
	}

	public Object getObject() {
		return obj;
	}

	public void setObject(Object obj) {
		this.obj = obj;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}
	
}
