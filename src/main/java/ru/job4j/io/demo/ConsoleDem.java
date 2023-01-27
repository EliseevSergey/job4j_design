package ru.job4j.io.demo;

import java.io.Console;
import java.util.Arrays;

public class ConsoleDem {
    public static void main(String[] args) {
        String login;
        char[] charPassword;
        Console console = System.console();
        if (console == null) {
            System.out.println("Консоль недоступна");
            return;
        }
        System.out.println(console.charset());
        login = console.readLine("%s", "Введите логин: ");
        console.printf("Ваш логин: %s\n", login);
        charPassword = console.readPassword("%s", "Введите пароль: ");
        System.out.println("Ваш пароль: " + String.valueOf(charPassword));
        Arrays.fill(charPassword, ' ');
    }
}
