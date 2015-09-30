package com.shakhin.logger.data;

public class ConfHandler {
	protected String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return super.toString()+"\n"+name+"<>";
	}
}
