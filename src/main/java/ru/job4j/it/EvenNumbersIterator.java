package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.function.Consumer;

public class EvenNumbersIterator implements Iterator {
    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (index == data.length) {
            return false;
        }
        while (((data[index] % 2) != 0) && (index < (data.length - 2))) {
            index++;
        }
        return data[index] % 2 == 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

    @Override
    public void remove() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        data[index++] = 0;
    }

    @Override
    public void forEachRemaining(Consumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
