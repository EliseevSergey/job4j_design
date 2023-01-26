package ru.job4j.io.demo;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class StrJoin {
    public static void main(String[] args) {
        List<Integer> num = Arrays.asList(1, 2, 3, 4);
        StringJoiner strjnr = new StringJoiner(":");
        strjnr.add("Fred").add("George").add("Tor");
        strjnr.add("Akra").add("Nira").add("Zaba");
        System.out.println(strjnr);
        String desString = strjnr.toString();
        System.out.println(desString);
        String strdes3 = num.stream()
                .map(integer -> integer.toString())
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(strdes3);
    }
}
