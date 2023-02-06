package ru.job4j.io.demo;

import java.text.ParseException;

public class Mask {
    public static void main(String[] args) throws ParseException {
        String maskBefore = "*.?xt";
        String newMask = maskBefore.replace(".", "[.]").replace("*", ".*").replace("?", ".");
        System.out.printf("Before %s \nAfter %s", maskBefore, newMask);
    }
}
