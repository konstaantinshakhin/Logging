package com.shakhin.logger.manager;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.shakhin.logger.data.ConFileHandler;
import com.shakhin.logger.data.ConfHandler;
import com.shakhin.logger.data.ConfLogger;
import com.shakhin.logger.manager.confmanager.ConfManager;
import com.shakhin.logger.service.handler.Handler;
import com.shakhin.logger.service.handler.Impl.FileHandler;
import com.shakhin.logger.service.observer.Impl.*;


public class LogManager {
	
	private static ConfManager confManager= ObjectFactory.getPropConfManager();
	private static Map<String,ConfLogger> logConfMap = confManager.getMapConfLoggers();
	private static Map<String,Logger> logMap = new HashMap <String,Logger>();

	
	public static Logger getLogger(String pack) {
		
		Logger logger = null;

		if(logMap.containsKey(pack)){
			 logger=logMap.get(pack);
		}
		else {
			ConfLogger confLog = getLoggerConfig(pack);
			if(confLog.getLogName().equals("root")){
				confLog.setLogName(pack);
				logger = createLogger(confLog);
			}
			else{
				logger = createLogger(confLog);
				Logger rootLogger =createLogger(getLoggerConfig("root"));
				rootLogger.setLogName(pack);
				logger.setRootLogger(rootLogger);
				logger.setUseRootLogger(true);
			}

			logMap.put(logger.getLogName(),logger);
		}
		return logger;
	}
	
	public static Logger getLogger(Class cl){
		
		String pack=cl.getName();
		Logger logger= getLogger(pack);
		return logger;
	}
	
	private static ConfLogger getLoggerConfig(String name){
		
			if(logConfMap.containsKey(name)){
				return logConfMap.get(name);
			}
			else{
				return logConfMap.get("root");
			}
	}
	
	private static Logger createLogger(ConfLogger confLog){
		
		Logger logger = new Logger(confLog.getLogName());
		logger.setLevel(confLog.getLevel());
		Set <ConfHandler> handlerSet=confLog.getHandlers();
		
		for(ConfHandler confHandler : handlerSet){
			if(confHandler instanceof ConFileHandler){	
				ConFileHandler conFileHandler=(ConFileHandler)confHandler;
				Handler handler= ObjectFactory.getFileHandler();
				FileHandler fileHandler=(FileHandler)handler;
				fileHandler.setName(conFileHandler.getName());				
				fileHandler.setFileName(conFileHandler.getFileName());
				fileHandler.setFormater(conFileHandler.getFormate());
				try{
					fileHandler.init();
				}
				catch(IOException ex){
					ex.printStackTrace();
				}
				//to do: check it
				logger.addHandler(fileHandler);
			}
			else if(confHandler instanceof ConfHandler){
				Handler handler= ObjectFactory.getConsoleHandler();
				handler.setName(confHandler.getName());
				logger.addHandler(handler);
			}
		}
		return logger;
	}
	
}
