package com.agapovp.epam.spring.core.loggers;

import com.agapovp.epam.spring.core.configs.AppConfig.Event;

public interface EventLogger {

    void logEvent(Event event);
}
