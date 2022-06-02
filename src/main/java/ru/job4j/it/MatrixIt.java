package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (data[row].length == column && row < data.length - 1) {
            row++;
            column = 0;
        }
        return data[row].length > column;
    }

        @Override
    public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return data[row][column++];
        }
}

