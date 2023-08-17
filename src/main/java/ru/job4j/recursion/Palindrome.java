package ru.job4j.recursion;

public class Palindrome {
    private static boolean palindrome(String str) {
        boolean rsl = false;
        if (str.length() <= 1) {
            rsl = true;
        } else {
            if (str.charAt(0) == str.charAt(str.length() - 1)) {
                rsl = true;
                str = str.substring(1, str.length() - 1);
                System.out.println(str);
                palindrome(str);
            }
        }
        return rsl;
    }

    public static void main(String[] args) {

        System.out.println(palindrome("RACECAR"));
    }
}
