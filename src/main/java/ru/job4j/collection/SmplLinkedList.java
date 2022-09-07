package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

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
        modCount++;
        if (size == 0) {
            Node<E> firstNode = new Node<>(null, value, null);
            first = firstNode;
            last = firstNode;
        } else {
            Node<E> secondNode = new Node<>(last, value, null);
            last = secondNode;
        }
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
