package ru.job4j.ood.srp.demo;

import java.util.List;

public interface SequenceGenerator<T> {
    List<T> generate(int size);
}
