package main.appannotation;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CombinedEventLogger implements EventLogger {

    List<EventLogger> loggers;

    CombinedEventLogger(List<EventLogger> loggers){
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger eventLogger: loggers)
            eventLogger.logEvent(event);
    }
}
