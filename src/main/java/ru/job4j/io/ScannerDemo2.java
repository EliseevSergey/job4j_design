package ru.job4j.io;

import java.util.Scanner;

public class ScannerDemo2 {
    public static void main(String[] args) {
        String instr = "Name: Tom Age: 28 ID: 77";
        Scanner conin = new Scanner(instr);
        conin.useDelimiter("7");
        conin.findInLine("Age: ");
        if (conin.hasNext()) {
            System.out.println(conin.next());
        } else {
            System.out.println("fail");
            conin.close();
        }
    }
}
