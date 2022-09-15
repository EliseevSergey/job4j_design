package ru.job4j.collection;

import java.util.*;

public class SmplLinkedList<E> implements SmplLinkedListIterface<E> {
    transient Node<E> first;
    transient Node<E> last;
    transient int size;
    int modCount;

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev, E element,  Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> succ = first;
        for (int i = 0; i < index; i++) {
                succ = succ.next;
        }
        return succ.item;
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        return new Iterator<E>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public E next() {
                Node<E> pointer = first;
                E rsl = pointer.item;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (index > 0) {
                    rsl = pointer.next.item;
                }
                pointer = pointer.next;
                index++;
                return rsl;
            }
        };
    }
}
