package ru.job4j.io;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToTime {
    public static void main(String[] args) {
        String str = "15:01:30";
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
        try {
            Date date = formater.parse(str);
            System.out.println(date);
            data.
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
