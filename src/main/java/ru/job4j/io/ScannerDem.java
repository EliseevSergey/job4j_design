package ru.job4j.io;
import java.util.Scanner;

public class ScannerDem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
            double sum = 0;
            int i = 0;
            while (sc.hasNext()) {
                if (sc.hasNextDouble()) {
                    sum += sc.nextDouble();
                    i++;
                } else {
                    String end = sc.next();
                    String stop = "end";
                    if (stop.equals(end)) {
                        break;
                    } else {
                        System.out.println("input error");
                    }
                }
            }
        System.out.println(sum / i);
        }

}


