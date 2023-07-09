package ru.job4j.ood.lsp.prodstore;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractStore implements Store {

    public abstract List<Food> findBy(Predicate<Food> filter);

    @Override
    public abstract void add(Food food);
}
