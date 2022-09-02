package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    public int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void add(T value, Object[] box, int s) {
        //System.out.println("BEFORE s: " + s + ". Container.length BEFORE:" + container.length);
        if (s == container.length) {
            container = grow();
            //System.out.println("Inside>>>: " + container.length);
        }
        container[s] = value;
        size = s + 1;
        //System.out.println("AFTER s: " + s);
    }

    @Override
    public boolean add(T value) {
        modCount++;
        add(value, container, size);
        //System.out.println("modCount: " + modCount);
        //System.out.println("size: " + size);
        //System.out.println("container.length: " + container.length + " END________");
        return true;
    };

    private T[] grow() {
       T[] doubledContainer = Arrays.copyOf(container, container.length * 2);
       //System.out.println("DB lenght: " + doubledContainer.length);
    return doubledContainer;
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
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
            return size < container.length;
            }

            @Override
            public T next() {
                if (!hasNext())  {
                    throw new NoSuchElementException();
                }
                return container[size + 1];
            }
        };
    }

    public int count() {
        return modCount;
    }
}
