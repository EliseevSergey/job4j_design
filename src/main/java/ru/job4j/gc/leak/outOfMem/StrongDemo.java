package ru.job4j.gc.leak.outOfMem;

import java.util.concurrent.TimeUnit;

public class StrongDemo {
    public static void main(String[] args) throws InterruptedException {
        //example1();
        example2();
    }

    private static void example1() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            objects[i] = new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed!Because strong link became null. example-1");
                }
            };
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    private static void example2() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            Object object = new Object() {
                Object innerObject = new Object() {
                    @Override
                    protected void finalize() throws Throwable {
                        System.out.println("Remove inner object! Because out object strong link became null. example-2");
                    }
                };
            };
            objects[i] = object;
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }
}