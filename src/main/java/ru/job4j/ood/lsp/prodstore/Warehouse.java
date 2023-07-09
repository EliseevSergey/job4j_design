package ru.job4j.ood.lsp.prodstore;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Warehouse extends AbstractStore {
    private final List<Food> list = new ArrayList<>();

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return list.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public boolean accept(Food food) {
        boolean rsl = false;
        float fresh  = ControlQuality.fresh(food);
        if (fresh < 0.25) {
            rsl = true;
        }
        return rsl;
    }

    @Override
    public void add(Food food) {
        list.add(food);
    }
}
