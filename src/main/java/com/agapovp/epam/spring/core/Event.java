package com.agapovp.epam.spring.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

@Component
@Scope("prototype")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event {

    static int count = 0;

    int id;
    @Getter @Setter
    String message;
    Date date;
    DateFormat dateFormat;

    @Autowired
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
