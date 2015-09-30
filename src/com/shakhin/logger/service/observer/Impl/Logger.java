package com.shakhin.logger.service.observer.Impl;


import java.util.HashSet;
import java.util.Set;

import com.shakhin.logger.data.Level;
import com.shakhin.logger.data.LogRecord;
import com.shakhin.logger.service.handler.Handler;
import com.shakhin.logger.service.observer.*;

public class Logger implements iObserver {
	
	private Set<Handler> handlers = null;
	private Level level;
	private String logName="root";
	private Logger rootLogger=null;
	private boolean isUseRootLogger=false;
	
	public Logger(){
		this.handlers=new HashSet<Handler>();
	}
	
	public Logger(String pack){
		this();
		logName=pack;
	}
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void addHandler(Handler h) {
		handlers.add(h);
	}
	
	private boolean isLoggable(Level level){
		if(level.getNumber()>this.level.getNumber())
			return false;
			else return true;
	}
	
	public boolean isError(){
		return isLoggable(Level.ERROR);
	}
	
	public boolean isDebug(){
		return isLoggable(Level.DEBUG);
	}
	
	public boolean isWarning(){
		return isLoggable(Level.WARNING);
	}
	
	public boolean isInfo(){
		return isLoggable(Level.INFO);
	}
	
	public void notify(LogRecord record) {
		if (isLoggable(record.getLevel()))
			for(Handler h : handlers)
				h.publish(record);
	}
	
	public void error(String message,Exception e){
		LogRecord record = new LogRecord();
		record.setLogName(logName);
		record.setLevel(Level.ERROR);
		record.setMessage(message);
		record.setException(e);
		record.setObject(null);
		notify(record);
		if(isUseRootLogger)getRootLogger().notify(record);
	}
	
	public void warning(String message){
		LogRecord record = new LogRecord();
		record.setLogName(logName);
		record.setLevel(Level.WARNING);
		record.setMessage(message);
		record.setException(null);
		record.setObject(null);
		notify(record);
		if(isUseRootLogger) getRootLogger().notify(record);
	}
	
	public void debug(String message,Object obj){
		LogRecord record = new LogRecord();
		record.setLogName(logName);
		record.setLevel(Level.DEBUG);
		record.setMessage(message);
		record.setException(null);
		record.setObject(obj);
		notify(record);
		if(isUseRootLogger) getRootLogger().notify(record);
	}
	
	public void info(String message){
		LogRecord record = new LogRecord();
		record.setLogName(logName);
		record.setLevel(Level.INFO);
		record.setMessage(message);
		record.setException(null);
		record.setObject(null);
		notify(record);
		if(isUseRootLogger)getRootLogger().notify(record);
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String toString(){
		StringBuilder handler=new StringBuilder();
		for(Handler handl : handlers){
			handler=handler.append(":"+handl.getName());
		}
		return super.toString()+":"+logName+":"+rootLogger+isUseRootLogger+":"+level+":"+handlers;
	}

	public boolean isUseRootLogger() {
		return isUseRootLogger;
	}

	public void setUseRootLogger(boolean isUseRootLogger) {
		this.isUseRootLogger = isUseRootLogger;
	}

	public Logger getRootLogger() {
		return rootLogger;
	}

	public void setRootLogger(Logger rootLogger) {
		this.rootLogger = rootLogger;
	}
	
}
