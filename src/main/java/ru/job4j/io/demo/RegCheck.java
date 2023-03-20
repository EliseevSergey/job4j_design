package ru.job4j.io.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegCheck {
    public static void main(String[] args) {
        String str = "hibernate.connection.driver_class";
        Pattern pattern = Pattern.compile("\\w+$");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println(matcher.group(0));
        } else {
            throw new IllegalArgumentException(String.format("Key[%s] characters are out of [a-zA-Z0-9_]", str));
        }
        //String key = str.split("\\w+$")[0];
        //System.out.println(key);

    }
}
