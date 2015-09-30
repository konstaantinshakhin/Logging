package com.shakhin.logger.service.handler.Impl;

import com.shakhin.logger.data.LogRecord;
import com.shakhin.logger.service.handler.Handler;

public class ConsoleHandler extends Handler {

	@Override
	public void publish(LogRecord record) {
		
		name="Console";
		System.out.print(record);
	}

}
