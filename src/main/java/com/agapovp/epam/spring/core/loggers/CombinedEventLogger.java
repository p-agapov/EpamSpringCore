package com.agapovp.epam.spring.core.loggers;

import com.agapovp.epam.spring.core.configs.AppConfig.Event;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CombinedEventLogger implements EventLogger {

    List<EventLogger> loggers;

    @Autowired
    public CombinedEventLogger(@Qualifier("loggerList") List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }
}
