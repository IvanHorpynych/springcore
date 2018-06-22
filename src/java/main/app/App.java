package main.app;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    Map<EventType, EventLogger > eventLoggers;

    public App(Client client, EventLogger eventLogger,
               Map<EventType, EventLogger > eventLoggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.eventLoggers = eventLoggers;
    }

    public App(Client client, CacheFileEventLogger cacheFileEventLogger, String s) {
    }

    public void logEvent(Event event, EventType eventType) {
        EventLogger logger = eventLoggers.get(eventType);
        if(logger == null)
            logger = defaultLogger;
        logger.logEvent(event);
    }

    public Client getClient() {
        return client;
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx =
                new ClassPathXmlApplicationContext(
                        "spring.xml");

        App app = (App) ctx.getBean("app");
        Event event = (Event) ctx.getBean("event");
        event.setMessage(app.getClient().getGreeting());
        app.logEvent(event, EventType.INFO);

        ctx.close();
    }
}
