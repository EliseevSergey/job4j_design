package ru.job4j.collection;

import java.util.*;

public class SmplLinkedList<E> implements SmplLinkedListIterface<E> {
    transient Node<E> head;
    transient int size;
    int modCount;

    private static class Node<E> {
        E item;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> succ = head;
        for (int i = 0; i < index; i++) {
            succ = succ.next;
        }
        return succ.item;
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;

        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return head != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = head.item;
                if (head.next != null) {
                    head = head.next;
                    //rsl = head.item;
                }
                return rsl;
            }
        };
    }
}
