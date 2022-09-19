package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> newLastNode = new Node<>(value, null);
        if (head == null) {
            head = newLastNode;
        } else {
            Node<T> tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = newLastNode;
        }
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T rls = head.value;
        if (head.next != null) {
            Node<T> newHead = new Node(head.next.value, head.next.next);
            head = newHead;
        } else {
            head = null;
        }
        return rls;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return head != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T rsl = head.value;
                if (head.next != null) {
                    head = new Node<>(head.next.value, head.next.next);
                } else {
                    head = null;
                }
                return rsl;
                }
        };
    }
}

