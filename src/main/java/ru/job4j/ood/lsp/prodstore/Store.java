package ru.job4j.ood.lsp.prodstore;

public interface Store {
    boolean accept(Food food);

    void add(Food food);
}
