package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.function.Consumer;

public class EvenNumbersIterator implements Iterator {
    private final int[] data;
    private int nextEvenIndex = -1;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        int index = nextEvenIndex;
        while (index++ < data.length - 1) {
            if (data[index] % 2 == 0) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int index = nextEvenIndex;
        int before = nextEvenIndex;
        while (index++ < data.length) {
            if (data[index] % 2 == 0) {
                nextEvenIndex = index;
                break;
            }
        }
        if (before == nextEvenIndex) {
            throw new NoSuchElementException();
        }
        return data[nextEvenIndex];
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
