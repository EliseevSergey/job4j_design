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

    public boolean revert() {
        boolean rsl = head != null && head.next != null;
        if (rsl) {
            Node<T> center = head.next;
            while (center != null) {
                Node<T> after = center.next;
                center.next = head;
                head = center;
                center = after;
            }
        }
        return rsl;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T rsl = head.value;
        if (head.next != null) {
            var newHead = head.next;
            head.value = null;
            head.next = null;
            head = newHead;
        } else {
            head.value = null;
            head = null;
        }
    return rsl;
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
            Node<T> pointer = head;

            @Override
            public boolean hasNext() {
                return pointer != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T rsl = pointer.value;
                pointer = pointer.next;
                return rsl;
                }
        };
    }
}

