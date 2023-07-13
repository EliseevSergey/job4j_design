package ru.job4j.ood.lsp.prodstore;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    boolean accept(Food food);

    void add(Food food);

    List<Food> findBy(Predicate<Food> filter);
}
