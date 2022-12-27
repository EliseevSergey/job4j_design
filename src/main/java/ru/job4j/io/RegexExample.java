package ru.job4j.io;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("Job4j", Pattern.CASE_INSENSITIVE);
        Pattern pattern1 = Pattern.compile("учусь");

        String text1 = "Я учусь на Job4j";
        Matcher matcher1 = pattern1.matcher(text1);
        boolean isPresent1 = matcher1.matches();
        System.out.println(isPresent1);

        String text2 = "Я учусь на курсе Job4j  и Job4j и Job4j и Job4j";
        Matcher matcher = pattern.matcher(text2);
        while (matcher.find()) {
            System.out.println("найдено совпадение " + matcher.start() + matcher.group() + matcher.end());

        }

        System.out.println(matcher.replaceAll("учусЬ"));

        System.out.println(matcher.find());

        String text3 = "Я учусЬ на JOB4J";
        Matcher matcher3 = pattern.matcher(text3);
        System.out.println(matcher3.matches());

        Pattern pattern4 = Pattern.compile("11");
        String text4 = "111111";
        Matcher matcher4 = pattern4.matcher(text4);
        while (matcher4.find()) {
            System.out.println("Найдено совпадение: " + matcher4.group());
        }

        String str = "123+=-456:/789";
        String[] rsl = str.split("\\D+");
        System.out.println(Arrays.toString(rsl));

        Pattern pattern5 = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
        String text5 = "24.04.1987 11.11.111111 99.99.99991 99.99.9999 99999999 0000.00.00";
        Matcher matcher5 = pattern5.matcher(text5);
        while (matcher5.find()) {
            System.out.println("Найдено совпадение5: " + matcher5.group());
        }

        Pattern pattern6 = Pattern.compile("\\S{1,}@\\S{1,}\\.\\S{1,}");
        String text6 = "peter-2022@example.com example65@mail_box.ru 123_45@example-mailbox.com";
        Matcher matcher6 = pattern6.matcher(text6);
        while (matcher6.find()) {
            System.out.println("Найдено совпадение: " + matcher6.group());
        }
    }
}

