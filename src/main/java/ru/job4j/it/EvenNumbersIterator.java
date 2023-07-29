package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.function.Consumer;

public class EvenNumbersIterator implements Iterator {
    private final int[] data;
    private int nextEvenIndex = -1;
    private int startToCheckIndex = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        int index = startToCheckIndex;
        while (index < data.length) {
            if (data[index] % 2 == 0) {
                rsl = true;
                nextEvenIndex = index;
                break;
            } else {
                index++;
            }
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        startToCheckIndex = nextEvenIndex + 1;
        return data[nextEvenIndex];
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
