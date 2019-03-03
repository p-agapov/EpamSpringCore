package com.agapovp.epam.spring.core.loggers;

import com.agapovp.epam.spring.core.configs.AppConfig.Event;
import org.springframework.stereotype.Component;

@Component
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {

        System.out.println(event.toString());
    }
}
