package com.agapovp.epam.spring.core.configs;

import com.agapovp.epam.spring.core.enums.EventType;
import com.agapovp.epam.spring.core.loggers.CombinedEventLogger;
import com.agapovp.epam.spring.core.loggers.ConsoleEventLogger;
import com.agapovp.epam.spring.core.loggers.EventLogger;
import com.agapovp.epam.spring.core.loggers.FileEventLogger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.text.DateFormat;
import java.util.*;

import static com.agapovp.epam.spring.core.enums.EventType.ERROR;
import static com.agapovp.epam.spring.core.enums.EventType.INFO;

@Configuration
@ComponentScan("com.agapovp.epam.spring.core")
@PropertySource("classpath:client.properties")
public class AppConfig {

    @Bean
    Client client(@Value("${id}") String id,
                  @Value("${name}") String name) {

        return new Client(id, name);
    }

    @Bean
    @Scope("prototype")
    Event event(Date date,
                DateFormat dateFormat) {

        return new Event(date, dateFormat);
    }

    @Bean("loggerList")
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

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Client {

        String id;
        String fullName;
        @Getter
        String greeting;

        @Value("${greeting}")
        public void setGreeting(String greeting) {
            this.greeting = greeting;
        }

        public Client(String id,
                      String fullName) {

            this.id = id;
            this.fullName = fullName;
        }
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Event {

        static int count = 0;

        int id;
        @Getter @Setter
        String message;
        Date date;
        DateFormat dateFormat;

        public Event(Date date, DateFormat dateFormat) {

            id = ++count;
            this.date = date;
            this.dateFormat = dateFormat;
        }

        @Override
        public String toString() {

            return String.format("#%d: %s : %s", id, dateFormat.format(date), message);
        }
    }
}
