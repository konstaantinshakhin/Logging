package com.shakhin.logger.service.handler;

import com.shakhin.logger.data.LogRecord;

public abstract class Handler {
	
	protected String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public abstract void  publish(LogRecord record);
	
}
