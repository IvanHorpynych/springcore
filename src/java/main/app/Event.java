package main.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    int id;
    String message;
    Date date;
    DateFormat df;

    Event(Date date, DateFormat df){
        this.id = new Random().nextInt(Integer.MAX_VALUE);
        this.date = date;
        this.df = df;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Event {\n" +
                "id: "+id+";\n"+
                "message: "+message+";\n"+
                "date: "+df.format(date)+";\n"+
                "}\n";
    }
}
