package ru.job4j.collection;

public interface SimpleList<T> extends Iterable<T> {
    boolean add(T value);
    T set(int index, T newValue);
    T remove(int index);
    Object get(int index);
    int size();
}
