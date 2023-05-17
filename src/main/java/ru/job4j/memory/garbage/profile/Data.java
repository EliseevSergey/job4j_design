package ru.job4j.memory.garbage.profile;

public interface Data {
    void insert(int elements);
    int[] getClone();
}