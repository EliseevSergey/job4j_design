package ru.job4j.collection;

public class SimpleStack<T> {
    final ForwardLinked linked = new ForwardLinked<T>();
    public int size = 0;

    public T pop() {
        size--;
        return (T) linked.deleteFirst();
    }

    public void push(T value) {
        size++;
        linked.addFirst(value);
    }
}
