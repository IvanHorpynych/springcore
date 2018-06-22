package main.app;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    List<Event> cache;
    int cacheSize;

    private CacheFileEventLogger(String filePath, String encoding, int cacheSize) {
        super(filePath, encoding);
        this.cache = new ArrayList<>();
        this.cacheSize = cacheSize;
    }

    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() >= cacheSize) {
            writeEventFromCache();
            cache.clear();
        }
    }

    private void writeEventFromCache() {
        for (Event event : cache) {
            super.logEvent(event);
        }
    }

    private void destroy(){
        if(!cache.isEmpty())
            writeEventFromCache();
        System.out.println("destroy");
    }
}
