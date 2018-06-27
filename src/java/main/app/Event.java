package main.app;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public static boolean isDay(){
       int currHour = Integer.valueOf(
               DateTimeFormatter.ofPattern("HH").format(LocalDateTime.now()));
       if(currHour>=8 && currHour<=16)
           return true;
       return false;
    }

    @Override
    public String toString() {
        return "Event {\n" +
                "id: "+id+";\n"+
                "message: "+message+";\n"+
                "date: "+df.format(date)+";\n"+
                "}\n";
    }

    public static void main(String[] args) {
        System.out.println(Event.isDay());
    }
}
