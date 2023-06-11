package ru.job4j.gc.leak.mem;

import java.util.ArrayList;
import java.util.List;

public class Example {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        System.out.println("Please, wait for out of memory");
        while (true) {
            strings.add(String.valueOf(System.currentTimeMillis()));
        }
    }
}