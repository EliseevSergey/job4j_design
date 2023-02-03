package ru.job4j.io.demo;

import java.io.Console;
import java.util.Arrays;

public class ConsoleDem {
    public static void main(String[] args) {
        Console console = System.console();
        if (console == null) {
            System.out.println("console == null >>>> no console is available");
        }
    }
}
