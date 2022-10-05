package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> itr = list.listIterator();
        while (itr.hasNext()) {
            if (itr.previousIndex() == index) {
                itr.add(value);
                break;
            }
            itr.next();
        }
    }

    public static <T> void removeIf(List<T> list, Predicate<T> predicate) {
        ListIterator<T> itr = list.listIterator();
        while (itr.hasNext()) {
            if (predicate.test(itr.next())) {
                itr.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> predicate, T value) {
        ListIterator<T> itr = list.listIterator();
        while (itr.hasNext()) {
            if (predicate.test(itr.next())) {
                itr.set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> itr = list.listIterator();
        while (itr.hasNext()) {
            T itemToCheckMain = itr.next();
            ListIterator<T> deleteItr = elements.listIterator();
            while (deleteItr.hasNext()) {
                if (deleteItr.next() == itemToCheckMain) {
                    itr.remove();
                }
            }
        }
    }
}
