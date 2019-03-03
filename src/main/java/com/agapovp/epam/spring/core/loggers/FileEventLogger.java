package com.agapovp.epam.spring.core.loggers;

import com.agapovp.epam.spring.core.configs.AppConfig.Event;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@PropertySource("classpath:loggers.properties")
public class FileEventLogger implements EventLogger {

    String fileName;

    FileEventLogger(@Value("${filepath}") String fileName) {

        this.fileName = fileName;
    }

    @PostConstruct
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
