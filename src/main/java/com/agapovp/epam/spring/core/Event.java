package com.agapovp.epam.spring.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.text.DateFormat;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event {

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
