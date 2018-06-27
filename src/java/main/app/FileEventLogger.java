package main.app;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private String filePath;
    private File file;
    private String encoding;

    public File getFile() {
        return file;
    }

    public FileEventLogger(String filePath, String encoding) {
        this.filePath = filePath;
        this.encoding = encoding;
    }

    private void init() throws IOException {
        this.file = new File(filePath);
        System.out.println("init");
        file.createNewFile();
        if (!file.canWrite())
            throw new IOException("Not have access to log file!");
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(new File(filePath), event.toString(), encoding, true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
