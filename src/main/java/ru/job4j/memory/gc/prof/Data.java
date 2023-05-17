package ru.job4j.memory.gc.prof;

public interface Data {
    void insert(int elements);
    int[] getClone();
}