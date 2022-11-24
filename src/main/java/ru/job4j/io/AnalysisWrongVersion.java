package ru.job4j.io;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AnalysisWrongVersion {
    public void unavailableWrongVersion(String source, String target) {
        TreeMap<Date, String> map = new TreeMap<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            List<String> list = in.lines().toList();
            for (String str : list) {
                String[] array = str.split(" ", 2);
                SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
                try {
                    Date date = formater.parse(array[1]);
                    map.put(date, array[0]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            int printSwitcher = 0;
            var strjnr = new StringJoiner(";");
            var formater = new SimpleDateFormat("HH:mm:ss");
            for (Date date : map.keySet()) {
                if ((map.get(date).equals("400") || map.get(date).equals("500")) && (printSwitcher == 0)) {
                    strjnr.add(formater.format(date));
                    printSwitcher = 1;
                }
                if (!map.get(date).equals("400") && !map.get(date).equals("500") && (printSwitcher == 1)) {
                    strjnr.add(formater.format(date));
                    out.println(strjnr);
                    strjnr = new StringJoiner(";");
                    printSwitcher = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AnalysisWrongVersion serverFailure = new AnalysisWrongVersion();
        serverFailure.unavailableWrongVersion("./data/server.txt", "./data/unavailableServer.csv");
    }
}


