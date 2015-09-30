package com.shakhin.logger.manager.confmanager.Impl;

import java.util.HashMap;
import java.util.Map;

import com.shakhin.logger.data.ConFileHandler;
import com.shakhin.logger.data.ConfHandler;
import com.shakhin.logger.data.ConfLogger;
import com.shakhin.logger.data.Formate;
import com.shakhin.logger.data.Level;
import com.shakhin.logger.manager.confmanager.ConfManager;

public class MemoryConfManager implements ConfManager {
	
	private static Map<String, ConfLogger> confMapLogger=new HashMap<String, ConfLogger>();
	private static ConfLogger confLogger=null;
	private static ConfHandler handler=null;
	private static ConFileHandler fhandler=null;
	
	static{
		
		confLogger=new ConfLogger();
		confLogger.setLogName("parent");
		confLogger.setLevel(Level.INFO);
		
		handler=new ConfHandler();
		handler.setName("Console");
		confLogger.addConfHandler(handler);
		
		handler=new ConFileHandler();
		fhandler=(ConFileHandler)handler;
		fhandler.setName("file");
		fhandler.setFileName("file.txt");
		fhandler.setFormate(Formate.XML);
		confLogger.addConfHandler((ConfHandler)fhandler);
		
		confMapLogger.put(confLogger.getLogName(),confLogger);
		
		confLogger=new ConfLogger();
		confLogger.setLogName("com.myapp.controller.FrontController");
		confLogger.setLevel(Level.DEBUG);
		
		handler=new ConfHandler();
		handler.setName("Console");
		confLogger.addConfHandler(handler);
		
		handler=new ConFileHandler();
		fhandler=(ConFileHandler)handler;
		fhandler.setName("debugfile");
		fhandler.setFileName("my_file_FrontController.txt");
		fhandler.setFormate(Formate.TXT);
		confLogger.addConfHandler((ConfHandler)fhandler);
		
		confMapLogger.put(confLogger.getLogName(),confLogger);
		
		confLogger=new ConfLogger();
		confLogger.setLogName("com");
		confLogger.setLevel(Level.WARNING);
		
		handler=new ConfHandler();
		handler.setName("Console");
		confLogger.addConfHandler(handler);
		
		handler=new ConFileHandler();
		fhandler=(ConFileHandler)handler;
		fhandler.setName("debugfile2");
		fhandler.setFileName("my_file_com.txt");
		fhandler.setFormate(Formate.HTML);
		confLogger.addConfHandler((ConfHandler)fhandler);
		
		confMapLogger.put(confLogger.getLogName(),confLogger);
	}

	@Override
	public Map<String, ConfLogger> getMapConfLoggers() {
		// TODO Auto-generated method stub
		return confMapLogger;
	}
}
