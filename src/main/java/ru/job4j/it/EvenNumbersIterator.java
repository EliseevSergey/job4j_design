package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.function.Consumer;

public class EvenNumbersIterator implements Iterator {
    private final int[] data;
    private int nextEvenIndex = 0;
    private int[] evens;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
     int index = nextEvenIndex;
        while (index < data.length && data[index] % 2 == 0) {
            index++;

            }
        return index < data.length;
        }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[nextEvenIndex++];
    }

    @Override
    public void remove() {
        next();
        data[nextEvenIndex] = 0;
    }

    @Override
    public void forEachRemaining(Consumer action) {
        Iterator.super.forEachRemaining(action);
    }

}
