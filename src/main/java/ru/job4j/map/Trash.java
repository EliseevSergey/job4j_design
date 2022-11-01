package ru.job4j.map;

public class Trash {
    public static void main(String[] args) {
        int count = 4;
        int capacity = 8;
        float lf = 0.75f;
        boolean sw = count * 100 / capacity >= lf * 100;
        System.out.println(sw);
        System.out.println(count * 100);
        System.out.println(count * 100 / capacity);
    }
}
