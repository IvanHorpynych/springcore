package main.appannotation;

import main.app.App;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@PropertySource({"classpath:client.properties",
        "classpath:logger.properties"})
public class AppConfig {

    @Bean(name = {"appAnnotation", "app"})
    AppAnnotation appAnnotation() {
        return new AppAnnotation(client(),
                cachedFileEventLogger(), loggerMap());
    }


    @Bean
    public Client client() {
        return new Client();
    }

    @Bean
    Event event() {
        return new Event();
    }

    @Bean
    EventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean
    EventLogger fileEventLogger() {
        return new FileEventLogger();
    }

    @Bean(name = {"cachedFileEventLogger", "defaultLogger"})
    EventLogger cachedFileEventLogger() {
        return new CacheFileEventLogger();
    }

    @Bean
    EventLogger combinedEventLogger() {
        return new CombinedEventLogger(loggers());
    }

    @Bean
    Map<EventType, EventLogger> loggerMap() {
        Map<EventType, EventLogger> loggerMap =
                new HashMap<>();
        loggerMap.put(EventType.INFO, consoleEventLogger());
        loggerMap.put(EventType.ERROR, combinedEventLogger());
        return loggerMap;
    }

    @Bean
    List<EventLogger> loggers() {
        List<EventLogger> loggers =
                new ArrayList<>();
        loggers.add(consoleEventLogger());
        loggers.add(fileEventLogger());
        return loggers;
    }
}
