package ru.job4j.generics;

import java.util.*;

public class GenericUsage {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        String o = (String) list.get(1);
        list.add(new Person("Zahar", 31, new Date(1988 - 05 - 12)));
        System.out.println(list.size());
        List<Integer> l = List.of(1, 2, 3, 4);
        new GenericUsage().printRsl(l);

        List<Person> per = List.of(new Person("Ivan", 31, new Date(1988 - 05 - 12)));
        new GenericUsage().printInfo(per);

        List<Programmer> pro = List.of(new Programmer("Kolya", 28, new Date(1986 - 05 - 12)));
        new GenericUsage().printInfo(pro);
    }

    public void printRsl(Collection<?> col) {
        for (Iterator<?> it = col.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println(next);
        }
    }

    public void printInfo(Collection<? extends Person> col) {
        for (Iterator<? extends Person> it = col.iterator(); it.hasNext();) {
            Person next = it.next();
            System.out.println(next);
        }
    }
}

