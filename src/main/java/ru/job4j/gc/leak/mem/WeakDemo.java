package ru.job4j.gc.leak.mem;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeakDemo {

    public static void main(String[] args) throws InterruptedException {
        //example1();
        //example2();
        example5();
    }

    private static void example1() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        WeakReference<Object> weak = new WeakReference<>(object);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(weak.get());
    }

    private static void example2() throws InterruptedException {
        List<WeakReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            objects.add(new WeakReference<Object>(new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Removed!");
                }
            }));
        }
        System.gc();
        TimeUnit.SECONDS.sleep(3);
    }

    private static void example5() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weak = new WeakReference<>(object, queue);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("from link because GC done " + weak.get());
        System.out.println("from queue. despite of GC done " + queue.poll());

        Object toBeCached = new Object();
        SoftReference<Object> softReference = new SoftReference<>(toBeCached);
        Object strongExtracted = softReference.get();
        if (strongExtracted != null) {
            System.out.println("bla bla");
        } else {
            System.out.println("bla bla");
        }
    }

}
