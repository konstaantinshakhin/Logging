package com.shakhin.logger.data;

import java.util.HashSet;
import java.util.Set;

public class ConfLogger {
	
	private String logName;
	private Set <ConfHandler> handlers = new HashSet<ConfHandler>();
	private Level level;
	
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public Set <ConfHandler> getHandlers() {
		return handlers;
	}
	public void setHandlers(Set <ConfHandler> handlers) {
		this.handlers = handlers;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void addConfHandler(ConfHandler handler){
		handlers.add(handler);
	}
	public String toString(){
		StringBuilder handler=new StringBuilder();
		for(ConfHandler handl : handlers){
			handler=handler.append(":"+handl.getName());
		}
		return super.toString()+":"+logName+":"+level+":"+handler+"\n";
	}
}
