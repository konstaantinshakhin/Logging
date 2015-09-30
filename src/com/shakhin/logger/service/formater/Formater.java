package com.shakhin.logger.service.formater;

import com.shakhin.logger.data.LogRecord;

public interface Formater {
	
	String getHeader();
	String getFooter();
	String getTagMessage(LogRecord record);

}
