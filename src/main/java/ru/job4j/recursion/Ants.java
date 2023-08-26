package ru.job4j.recursion;

public class Ants {
    public static int moveCounter(int n, int left, int right) {
        int moveCounter = 0;
        if (left < right) {
            int distToEdgeLeft = left;
            int distToEdgeRight = n - right;
            boolean shorter = distToEdgeLeft <= distToEdgeRight;
            moveCounter = shorter ? distToEdgeLeft + 1 : distToEdgeRight + 1;
        } else {
            int distToFace = left - right;
            int movesToFace = distToFace / 2;
            int leftBeforeSwap;
            int rightBeforeSwap;
            if (distToFace % 2 == 1) {
                leftBeforeSwap = left - movesToFace;
                rightBeforeSwap = right + movesToFace;
            } else {
                leftBeforeSwap = left - movesToFace;
                rightBeforeSwap = right - movesToFace;
            }
            moveCounter = movesToFace + moveCounter(n, rightBeforeSwap, leftBeforeSwap);
        }
        return moveCounter;
    }

    public static void main(String[] args) {
        System.out.println("Пример 1. Total to be 2 : Real total: " + moveCounter(4, 1, 3));
        System.out.println("Пример 2. Total to be 3 : Real total: " + moveCounter(4, 4, 1));
        System.out.println("Пример 3. Total to be 1 : Real total: " + moveCounter(4, 4, 3));
        System.out.println("Пример 4. Total to be 2 : Real total: " + moveCounter(3, 3, 1));

    }
}
