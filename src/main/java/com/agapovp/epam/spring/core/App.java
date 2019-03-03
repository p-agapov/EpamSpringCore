package com.agapovp.epam.spring.core;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.agapovp.epam.spring.core.EventType.INFO;
import static com.agapovp.epam.spring.core.EventType.ERROR;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class App {

    Client client;
    EventLogger eventLogger;
    Map<EventType, EventLogger> loggers;

    @Autowired
    public App(Client client,
               @Qualifier("cacheFileEventLogger") EventLogger eventLogger,
               Map<EventType, EventLogger> loggers) {

        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        App app = (App) context.getBean("app");

        app.logEvent("Some event for user 1", null);
        app.logEvent("Some event for user 2", null);
        app.logEvent("Some error for user 1", INFO);
        app.logEvent("Some error for user 2", INFO);
        app.logEvent("Some info for user 1", ERROR);
        app.logEvent("Some info for user 2", ERROR);
        app.logEvent("Some error for user 1", null);
        app.logEvent("Some error for user 2", null);

        context.close();
    }

    private void logEvent(String msg, EventType type) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Event event = (Event) context.getBean("event");

        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMessage(message);

        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = eventLogger;
        }
        logger.logEvent(event);
    }
}
