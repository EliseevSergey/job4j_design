package ru.job4j.ood.lsp.prodstore;

public class Warehouse extends AbstractStore {
    public boolean accept(Food food) {
        return food.getFreshLevel() < warehouse;
    }
}
