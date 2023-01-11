package ru.job4j.io;


import java.io.Console;

public class ConsoleDem {
    public static void main(String[] args) {
        String str;
        Console con;
        con = System.console();
        if (con == null) {
            System.out.println("no console. IDEA general issue");
            return;
        }
        str = con.readLine("Test line: ");
        con.printf("Inputted str ", str);
    }
}
