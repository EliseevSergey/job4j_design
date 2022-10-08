package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {
    private final SimpleArrayList set = new SimpleArrayList<T>(0);

    @Override
    public boolean add(T value) {
        boolean rsl = true;
        Iterator<T> itr = set.iterator();
        while (itr.hasNext()) {
            if (Objects.equals(itr.next(), value)) {
                rsl = false;
                break;
            }
        }
        if (rsl) {
            set.add(value);
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        Iterator<T> itr = set.iterator();
        while (itr.hasNext()) {
            if (Objects.equals(itr.next(), value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}