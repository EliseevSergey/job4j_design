package ru.job4j.it.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal("Pozvonochnie"));
        first.add(new Predator("Koshachie"));
        first.add(new Tiger("Tigra"));

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

        /*gen.printBoundedWildCard(first);*/
        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println();

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);
        /*gen.printLowerBoundedWildCard(third);*/
    }

    public void printObject(List<?> list) {
        for (Iterator<?> it = list.listIterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println(next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Animal> it = list.listIterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println(next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.listIterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println(next);
        }
    }
}
