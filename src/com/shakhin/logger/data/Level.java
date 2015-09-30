package com.shakhin.logger.data;

	public enum Level {ERROR(1),WARNING(2),DEBUG(3),INFO(4),ALL(5),OFF(0);
	
	private Level(int num){
		this.num = num;
	}
	private int num;

	public int getNumber() {
		return num;
		}
	
	public static Level toLevel(String level){
		switch(level){
			case "ERROR" : return Level.ERROR;
			case "WARNING" : return Level.WARNING;
			case "INFO" : return Level.INFO;
			case "DEBUG" : return Level.DEBUG;
			case "ALL" : return Level.ALL;
			case "OFF" : return Level.OFF;
			default : return Level.WARNING;
			}
		}
	
	}
	


