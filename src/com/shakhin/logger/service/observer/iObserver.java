package com.shakhin.logger.service.observer;


import com.shakhin.logger.data.LogRecord;
import com.shakhin.logger.service.handler.Handler;

public interface iObserver {
	
	void addHandler(Handler h);
	void notify(LogRecord record);

}
