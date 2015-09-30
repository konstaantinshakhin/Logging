package com.shakhin.logger.service.formater.Impl;

import com.shakhin.logger.data.LogRecord;
import com.shakhin.logger.service.formater.Formater;

public class TXTFormater implements Formater {

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return "**************************************************************************************************************";
	}

	@Override
	public String getFooter() {
		// TODO Auto-generated method stub
		return "***************************************************************************************************************";
	}

	@Override
	public String getTagMessage(LogRecord record) {
		// TODO Auto-generated method stub
		return record.toString();
	}

}
