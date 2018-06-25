package main.appannotation;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/*@Component("app")*/
public class AppAnnotation {

    private Client client;
    private EventLogger defaultLogger;
    Map<EventType, EventLogger > eventLoggers;

    public AppAnnotation(Client client,EventLogger defaultLogger,
                         Map<EventType, EventLogger > eventLoggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.eventLoggers = eventLoggers;
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

        /*ConfigurableApplicationContext ctx =
                new ClassPathXmlApplicationContext(
                        "springAnnotation.xml");*/
        ConfigurableApplicationContext ctx =
                new AnnotationConfigApplicationContext(
                        AppConfig.class);

        AppAnnotation app = (AppAnnotation) ctx.getBean("app");
        Event event = (Event) ctx.getBean("event");
        event.setMessage(app.getClient().getGreeting());
        app.logEvent(event, EventType.ERROR);

        ctx.close();
    }
}
