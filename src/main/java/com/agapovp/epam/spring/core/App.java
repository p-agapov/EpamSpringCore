package com.agapovp.epam.spring.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class App {

    Client client;
    EventLogger eventLogger;

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) context.getBean("app");

        app.logEvent("Some event for user 1");
        app.logEvent("Some event for user 2");
    }

    private void logEvent(String msg) {

        ApplicationContext context =new ClassPathXmlApplicationContext("spring.xml");
        Event event = (Event) context.getBean("event");

        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMessage(message);
        eventLogger.logEvent(event);
    }
}
