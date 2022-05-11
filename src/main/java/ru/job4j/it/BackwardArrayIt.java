package ru.job4j.it;

import java.util.Iterator;

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
       return data.length - point > 0;
    }

    @Override
    public Integer next() {
        return data[data.length - 1 - point++];
    }
}
