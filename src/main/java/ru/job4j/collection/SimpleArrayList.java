package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    public int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void add(T value, Object[] box, int s) {
        if (s == container.length) {
            container = grow();
        }
        container[s] = value;
        size = s + 1;
    }

    @Override
    public boolean add(T value) {
        modCount++;
        add(value, container, size);
        return true;
    }

    private T[] grow() {
        return Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
         Objects.checkIndex(index, size);
         T remItem = container[index];
         System.arraycopy(
                 container,
                 index + 1,
                 container,
                 index,
                 container.length - index - 1
                 );
         size = size - 1;
        modCount++;
        return remItem;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<T>() {
            int point = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            return point < size;
            }

            @Override
            public T next() {
                if (!hasNext())  {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }

    public int count() {
        return modCount;
    }
}
