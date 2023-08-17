package ru.job4j.recursion;

public class Fibonachi {
    private int counter = 0;
    private int fib(int mbr) {
        int rsl;
        if (mbr <= 2) {
            rsl = 1;
        } else {
            counter++;
            System.out.println(counter);
            System.out.println("Recursion call " + mbr);
            rsl = fib(mbr - 1) + fib(mbr - 2);
        }
        return rsl;
    }

    public static void main(String[] args) {
        Fibonachi fb = new Fibonachi();

        System.out.println(fb.fib(100));
    }
}
