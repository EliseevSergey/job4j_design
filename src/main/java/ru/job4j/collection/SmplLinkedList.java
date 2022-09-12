package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

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



/*    @Override
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
    }*/

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
        //Objects.checkIndex(index, size);
        Node<E> rsl = first;
        /*for (int i = 0; i < size; i++) {
            rsl = rsl.next;
        }*/
        return rsl.item;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
