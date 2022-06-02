package ru.job4j.it;

public class ArraExplorer {
    public static void main(String[] args) {
        int[][] arrayEmpty = {{}};
        System.out.println("length of empty [][] array : " + arrayEmpty.length);
        System.out.println("length of internal massive : " + arrayEmpty[0].length);
        System.out.println(arrayEmpty[0][0]);
        int[][] fat = {{}, {1}};
        System.out.println("length of {{}, {1}} : " + fat.length);
        System.out.println(fat[0][0]);
    }
}
