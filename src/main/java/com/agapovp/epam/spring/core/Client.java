package com.agapovp.epam.spring.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@PropertySource("classpath:client.properties")
public class Client {

    String id;
    String fullName;
    @Getter
    String greeting;

    @Value("${greeting}")
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public Client(@Value("${id}") String id,
                  @Value("${name}") String fullName) {

        this.id = id;
        this.fullName = fullName;
    }
}
