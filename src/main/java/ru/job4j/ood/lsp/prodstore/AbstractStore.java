package ru.job4j.ood.lsp.prodstore;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractStore implements Store {
    float warehouse = 0.25f;
    float shopDiscount = 0.75f;
    float trash = 1;
    private final List<String> storagelist = new ArrayList<>();

    @Override
    public abstract boolean accept(Food food);
    @Override
    public abstract List<Food> findBy(Predicate<Food> filter);

    @Override
    public abstract void add(Food food);
}
