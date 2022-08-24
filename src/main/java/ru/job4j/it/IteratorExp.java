package ru.job4j.it;

import java.util.Iterator;
import java.util.List;

public class IteratorExp {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        Iterator<Integer> iterator = list.iterator();
        /**
        //System.out.println(iterator.next());
        //System.out.println(iterator.next());
        /*while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }*/

        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 666, 444).iterator(),
                List.of(2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        /**
       data.next().forEachRemaining(digit -> System.out.println(digit));
        */
        data.next().forEachRemaining(System.out :: println);
        /**
        System.out.println(data.next().next());
        System.out.println(data.next().next());
        System.out.println(data.next().next());
         */
    }
}
