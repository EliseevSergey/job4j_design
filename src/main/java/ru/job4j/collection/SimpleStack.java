package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked linked = new ForwardLinked<T>();

    public T pop() {
        return (T) linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }
}
