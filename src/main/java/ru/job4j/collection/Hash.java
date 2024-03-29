package ru.job4j.collection;

public class Hash {
    public static String binary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 31; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 8 == 0 ? " " : "");
            num /= 2;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(Hash.binary(1137));
        System.out.println(Hash.binary(7));
        System.out.println(Hash.binary((72 & 7)));
        System.out.println("line");
        System.out.println(Hash.binary(255));
        System.out.println(Hash.binary(255 >> 4));
        System.out.println(Hash.binary(256));
        System.out.println(Hash.binary(Integer.MAX_VALUE));
        System.out.println(Hash.binary(Integer.MAX_VALUE >> 16));
        System.out.println(Hash.binary(2147483647));
    }
}
