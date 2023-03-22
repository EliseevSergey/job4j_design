package ru.job4j.io.demo;

import java.sql.SQLOutput;
import java.util.Arrays;

import static java.lang.System.out;

public class Split {
    public static void main(String[] args) {
        String str1 = "Petr Arsentev;parsentev@yandex.ru;";

        String str3 = "Petr Arsentev;";
        String str4 = ";parsentev@yandex.ru;";
        String str5 = ";";
        //String [] rsl = str1.split(";");
        //Arrays.stream(rsl).forEach(out::println);

        //String [] rsl2 = str3.split(";");
       // out.println(rsl2.length);
        //Arrays.stream(rsl2).forEach(out::println);

        String [] rsl4 = str4.split(";");
        out.println(rsl4.length);
        out.println(rsl4[0].isBlank());
        out.println(rsl4[1].isBlank());
        out.println(rsl4[0].length());

        out.println(String.format("this [%s] is 0 when starts with;", rsl4[0]));
        Arrays.stream(rsl4).forEach(out::println);

        /*String [] rsl5 = str5.split(";");
        out.println(rsl5.length);*/
    }
}
