package com.shakhin.logger.manager.confmanager.Impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.shakhin.logger.data.ConFileHandler;
import com.shakhin.logger.data.ConfHandler;
import com.shakhin.logger.data.ConfLogger;
import com.shakhin.logger.data.Formate;
import com.shakhin.logger.data.Level;
import com.shakhin.logger.manager.confmanager.ConfManager;

public class PropConfManager implements ConfManager {
	
	private Properties props = null;
	private static Map<String,String> mapProp = null;
	
	private  Map<String,String> getProperties(){
		
		props = new Properties();
		Map <String,String> mapProp = new HashMap <String,String>();
		try{
			props.load(getClass().getClassLoader().getResourceAsStream("log.properties"));
			Set <String> pname = props.stringPropertyNames();
			for(String key : pname){
				mapProp.put(key,props.getProperty(key));
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return mapProp;
	}  
	
		private Map <String,ConfHandler> getParseHandlers(){
			
		if(mapProp== null) mapProp=getProperties();
		Map <String,ConfHandler> mapH=new HashMap<String,ConfHandler>();
		ConfHandler confHandler=null;
		ConFileHandler conFileHandler=null;
		for(Entry<String, String> mapEntry : mapProp.entrySet()){
			String[] parse = mapEntry.getKey().split("\\.");
			if  (parse[0].equalsIgnoreCase("fileHandler")&(parse.length == 3)){
				if(mapH.containsKey(parse[1])) confHandler=mapH.get(parse[1]);
					else {
						confHandler=new ConFileHandler();
						confHandler.setName(parse[1]);
					}
				conFileHandler=(ConFileHandler)confHandler;
				conFileHandler.setFileName(mapEntry.getValue());
				mapH.put(parse[1],(ConfHandler)conFileHandler);
			}
			if (parse[0].equalsIgnoreCase("fileHandler") & (parse.length == 4)) {
				if(mapH.containsKey(parse[1])) confHandler=mapH.get(parse[1]);
					else {
						confHandler=new ConFileHandler();
						confHandler.setName(parse[1]);
					}
				conFileHandler=(ConFileHandler)confHandler;
				conFileHandler.setFormate(Formate.toFormate(mapEntry.getValue()));
				mapH.put(parse[1],(ConfHandler)conFileHandler);
			}
		}
		return mapH;
	}
	
	public Map<String,ConfLogger> getMapConfLoggers(){
		
		if(mapProp == null) mapProp=getProperties();
		ConfLogger confLogger = null;
		Map <String,ConfHandler> mapHand = getParseHandlers();
		Map <String,ConfLogger> mapLog = new HashMap <String,ConfLogger>();
		ConfHandler confHandler = null;
		for(Entry<String, String> mapEntry : mapProp.entrySet()){
			confLogger=new ConfLogger();
			String[] pkey = mapEntry.getKey().split("\\.");
			if (pkey[0].equalsIgnoreCase("rootLogger")) {
				confLogger.setLogName("root");
				String[] pvalue = mapEntry.getValue().split(",");
				confLogger.setLevel(Level.toLevel(pvalue[0]));
				int i=1;
				while(i<pvalue.length){
					if(mapHand.containsKey(pvalue[i].trim())){
					confHandler = mapHand.get(pvalue[i].trim());
						}
					else if(pvalue[i].equalsIgnoreCase("console")) {
						confHandler = new ConfHandler();
						confHandler.setName("Console");
						}
					confLogger.addConfHandler(confHandler);
					i++;
					}mapLog.put(confLogger.getLogName(), confLogger);
			}
			if (pkey[0].equalsIgnoreCase("logger")) {
			String[] pvalue = mapEntry.getValue().split(",");
			confLogger.setLogName(withOutHeader(mapEntry.getKey()));
			confLogger.setLevel(Level.toLevel(pvalue[0].trim()));
			int i=1;
			while(i<pvalue.length){
				if(mapHand.containsKey(pvalue[i].trim())){
				confHandler = mapHand.get(pvalue[i].trim());
					}
				else if(pvalue[i].equalsIgnoreCase("console")) {
					confHandler = new ConfHandler();
					confHandler.setName("Console");
					}
				confLogger.addConfHandler(confHandler);
				i++;
				}mapLog.put(confLogger.getLogName(), confLogger);
			}
			
		}
		return mapLog;
	}
	
	private  String withOutHeader(String name){

		int i=name.indexOf(".");
		return name.substring(i+1);
	}

}

