package com.agapovp.epam.spring.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Client {

    String id;
    String fullName;
    @Getter @Setter
    String greeting;

    public Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
