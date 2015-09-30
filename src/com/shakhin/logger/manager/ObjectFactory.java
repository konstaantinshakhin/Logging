package com.shakhin.logger.manager;

import com.shakhin.logger.manager.confmanager.ConfManager;
import com.shakhin.logger.manager.confmanager.Impl.MemoryConfManager;
import com.shakhin.logger.manager.confmanager.Impl.PropConfManager;
import com.shakhin.logger.service.formater.Formater;
import com.shakhin.logger.service.formater.Impl.TXTFormater;
import com.shakhin.logger.service.handler.Handler;
import com.shakhin.logger.service.handler.Impl.*;

public  class ObjectFactory {

	public static Handler getConsoleHandler(){
		
		return  new ConsoleHandler();
	}
	
	public static Handler getFileHandler(){
		
		return  new FileHandler();
	}
	
	public static ConfManager getPropConfManager(){
		
		return new PropConfManager();
	}
	
	public static ConfManager getMemoryConfManager(){
		
		return new MemoryConfManager();
	}
	
	public static Formater getXMLFormater(){
		
		return new TXTFormater();
	}
	
	public static Formater getTXTFormater(){
		
		return new TXTFormater();
		
	}
	public static Formater getHTMLFormater(){
	
		return new TXTFormater();
	}
}
