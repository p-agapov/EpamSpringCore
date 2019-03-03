package com.agapovp.epam.spring.core;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import java.text.DateFormat;
import java.util.*;

import static com.agapovp.epam.spring.core.EventType.ERROR;
import static com.agapovp.epam.spring.core.EventType.INFO;

@Configuration
@ComponentScan("com.agapovp.epam.spring.core")
public class AppConfig {

    @Bean
    @Qualifier("LoggerList")
    List<EventLogger> loggerList(ConsoleEventLogger consoleEventLogger,
                                 FileEventLogger fileEventLogger) {

        List<EventLogger> loggers = new ArrayList<>();

        loggers.add(consoleEventLogger);
        loggers.add(fileEventLogger);

        return loggers;
    }

    @Bean
    Map<EventType, EventLogger> loggerMap(ConsoleEventLogger consoleEventLogger,
                                          CombinedEventLogger combinedEventLogger) {

        Map<EventType, EventLogger> loggers = new HashMap<>();

        loggers.put(INFO, consoleEventLogger);
        loggers.put(ERROR, combinedEventLogger);

        return loggers;
    }

    @Bean
    Date date() {

        return new Date();
    }

    @Bean
    DateFormat dateFormat() {

        return DateFormat.getDateTimeInstance();
    }
}
