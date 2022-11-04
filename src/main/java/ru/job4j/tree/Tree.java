package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface Tree<E> {
    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E val);

    boolean isBinary();

    class Node<E> {
        final E val;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E val) {
            this.val = val;
        }

        public E getVal() {
            return val;
        }

        public List<Node<E>> getChildren() {
            return children;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return Objects.equals(val, node.val) && Objects.equals(children, node.children);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, children);
        }
    }
}
