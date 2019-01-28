package com.agapovp.epam.spring.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CombinedEventLogger implements EventLogger {

    Collection<EventLogger> loggers;

    @Override
    public void logEvent(Event event) {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }
}
