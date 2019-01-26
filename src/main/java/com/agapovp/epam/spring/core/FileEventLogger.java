package com.agapovp.epam.spring.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileEventLogger implements EventLogger {

    String fileName;

    private void init() throws IOException {

        File file = new File(fileName);

        if (!file.canWrite()) {
            throw  new IOException("Can not write to file!");
        }
    }

    @Override
    @SneakyThrows
    public void logEvent(Event event) {

        @Cleanup FileWriter fileWriter = new FileWriter(fileName, true);

        fileWriter.write(String.format("%s%n", event.toString()));
        fileWriter.flush();
    }
}
