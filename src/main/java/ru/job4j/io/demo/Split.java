package ru.job4j.io.demo;

import java.util.Arrays;

import static java.lang.System.out;

public class Split {
    public static void main(String[] args) {
        String str1 = "Petr Arsentev;parsentev@yandex.ru;";

        String str3 = "Petr Arsentev;";
        String str4 = ";parsentev@yandex.ru;";
        String str5 = ";";
        String str6 = ";;";
        out.println("this is for Petr Arsentev;parsentev@yandex.ru;");
        String[] rsl = str1.split(";");
        Arrays.stream(rsl).forEach(out::println);

        out.println("this is for \"Petr Arsentev;\" ");
        String[] rsl3 = str3.split(";");
        out.println(rsl3.length);
        Arrays.stream(rsl3).forEach(out::println);

        out.println("this is for \";parsentev@yandex.ru;\" ");
        String[] rsl4 = str4.split(";");
        out.println(rsl4.length);
        out.println(rsl4[0].isBlank());
        out.println(rsl4[1].isBlank());
        out.println(rsl4[0].length());

        out.printf("this [%s] is 0 when starts with;%n", rsl4[0]);
        Arrays.stream(rsl4).forEach(out::println);

        String[] rsl5 = str5.split(";");
        out.println(rsl5.length);


        out.println("this is ;;");
        String[] rsl6 = str6.split(";", 1);
        out.println(rsl6.length);
        out.println(rsl6[0].isBlank());
        out.println(rsl6[1].isBlank());
        out.println(rsl6[0].length());

        out.printf("this [%s] is 0 when starts with;%n", rsl6[0]);
        Arrays.stream(rsl6).forEach(out::println);
    }
}
