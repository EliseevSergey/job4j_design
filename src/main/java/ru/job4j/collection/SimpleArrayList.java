package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    public int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = grow();
        }
        container[size++] = value;
    }

    private T[] grow() {
        return container.length == 0 ? Arrays.copyOf(container, 1)
                : Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
         T remItem = get(index);
         System.arraycopy(
                 container,
                 index + 1,
                 container,
                 index,
                 container.length - index - 1
                 );
         size = size - 1;
        container[container.length - 1] = null;
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
}
