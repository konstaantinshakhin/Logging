package com.shakhin.logger.manager.confmanager;

import java.util.Map;

import com.shakhin.logger.data.ConfLogger;

public interface ConfManager {

	Map <String,ConfLogger> getMapConfLoggers();
}
