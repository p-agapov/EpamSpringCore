package com.agapovp.epam.spring.core;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(String message) {
        System.out.println(message);
    }
}
