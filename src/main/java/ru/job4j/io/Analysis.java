package ru.job4j.io;

import java.io.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> list = new ArrayList<>();
        TreeMap<Date, String> map = new TreeMap<>();
        try (BufferedReader in = new BufferedReader(new FileReader("./data/server.txt"))) {
            list = in.lines().toList();
            for (String str : list) {
                String[] array = str.split(" ", 2);
                SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
                try {
                    Date date = formater.parse(array[1]);
                    map.put(date, array[0]);
                    System.out.println(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            for (Date date : map.keySet()) {
                int switcher = 0;
                StringJoiner strjnr = new StringJoiner("PPPPPP");
                if ((map.get(date).equals("400") || map.get(date).equals("500")) && (switcher == 0)) {
                    SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
                    strjnr.add(toString());
                    out.print(strjnr.toString());
                    switcher = 1;
                } else {
                    switcher = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis fallServer = new Analysis();
        fallServer.unavailable("./data/server.txt", "./data/unavailableServer.txt");
    }

    /*public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
