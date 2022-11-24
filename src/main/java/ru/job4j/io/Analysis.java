package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            BufferedReader in = new BufferedReader(new FileReader(source));
            int printSwitcher = 0;
            var strjnr = new StringJoiner(";");
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] array = line.split(" ");
                if (((array[0].equals("400") || (array[0].equals("500"))) && (printSwitcher == 0))) {
                    strjnr.add(array[1]);
                    printSwitcher = 1;
                }
                if (!array[0].equals("400") && !array[0].equals("500") && printSwitcher == 1) {
                    strjnr.add(array[1]);
                    out.println(strjnr);
                    strjnr = new StringJoiner(";");
                    printSwitcher = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis fail = new Analysis();
        fail.unavailable("./data/server.txt", "./data/unavailableServer.csv");
    }
}

