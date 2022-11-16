package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            in.lines().forEach(System.out::println);
            List<String> listString = new ArrayList<>();
            listString.add(in.readLine());
            Iterator<String> itr = listString.listIterator();
            while(itr.hasNext()) {
                System.out.println(itr.next());
            }
            /*listString.stream()
                    .filter(item -> item.contains("404"))
                    .collect(Collectors.toList());*/
            //listString.stream().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }

}
