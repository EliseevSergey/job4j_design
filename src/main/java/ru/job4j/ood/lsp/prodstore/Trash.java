package ru.job4j.ood.lsp.prodstore;

public class Trash extends AbstractStore {
    @Override
    public boolean accept(Food food) {
        return food.getFreshLevel() >= trash;
    }
}
