package com.agapovp.epam.spring.core;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component("cacheFileEventLogger")
@FieldDefaults(level = AccessLevel.PRIVATE)
@PropertySource("classpath:loggers.properties")
public class CacheFileEventLogger extends FileEventLogger {

    int cacheSize;
    List<Event> cache = new ArrayList<>();

    public CacheFileEventLogger(@Value("${filepath}") String fileName,
                                @Value("${cachesize}") int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {

            for (Event e : cache) {
                super.logEvent(e);
            }
            cache.clear();
        }
    }

    @PreDestroy
    private void destroy() {
        if (!cache.isEmpty()) {
            for (Event e : cache) {
                super.logEvent(e);
            }
        }
    }
}
