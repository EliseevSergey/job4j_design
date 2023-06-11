package ru.job4j.gc.leak.mem;

import java.util.ArrayList;
import java.util.List;
import java.lang.ref.SoftReference;

public class SoftDemo {
    public static void main(String[] args) {
        //example3();
        example4();
    }

    private static void example3() {
        Object strong = new Object();
        SoftReference<Object> soft = new SoftReference<>(strong);
        strong = null;
        System.out.println("Soft link: " + soft.get());
    }

    private static void example4() {
        List<SoftReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100_000_000; i++) {
            objects.add(new SoftReference<Object>(new Object()) {
                String value = String.valueOf(System.currentTimeMillis());

                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object with soft link were deleted because heap is full. removed!");
                }
            });
        }
        System.gc();
        int liveObject = 0;
        for (SoftReference<Object> ref : objects) {
            Object object = ref.get();
            if (object != null) {
                liveObject++;
            }
        }
     System.out.println(liveObject);
    }
}
