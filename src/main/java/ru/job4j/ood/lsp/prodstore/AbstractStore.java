package ru.job4j.ood.lsp.prodstore;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractStore implements Store {
    protected float warehouse = 0.25f;
    protected float shopDiscount = 0.75f;
    protected float trash = 1;
    protected List<Food> list = new ArrayList<>();

    @Override
    public abstract boolean accept(Food food);

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return list.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public void add(Food food) {
        list.add(food);
    }
}
