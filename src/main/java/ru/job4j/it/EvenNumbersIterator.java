package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.function.Consumer;

public class EvenNumbersIterator implements Iterator {
    private final int[] data;
    private int nextEvenIndex = -1;
    private int startToCheckIndex = -1;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    private void nextEvenIndexSetUp() {
        int index = startToCheckIndex + 1;
        while (index < data.length) {
            if (data[index] % 2 == 0) {
                nextEvenIndex = index;
                break;
            } else {
                index++;
            }
        }
    }

    @Override
    public boolean hasNext() {
        nextEvenIndexSetUp();
        return startToCheckIndex < nextEvenIndex;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        startToCheckIndex = nextEvenIndex;
        return data[startToCheckIndex];
    }

    @Override
    public void remove() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        data[nextEvenIndex] = 0;
    }

    @Override
    public void forEachRemaining(Consumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
