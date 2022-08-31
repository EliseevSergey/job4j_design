package ru.job4j.collection;

import java.util.Iterator;

public class SimpleArrayList<T> implements SimpleList<T> {
    private Object[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void add(T value, Object[] container, int s) {
        if (s == container.length) {
            container = grow();
        }
        container[s] = value;
        size++;
    }

    @Override
    public boolean add(T value) {
        modCount++;
        add(value, container, size);
        return true;
    };

    @Override
    public T set(int index, T newValue) {
        return null;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }
}
