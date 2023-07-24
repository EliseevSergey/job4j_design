package ru.job4j.ood.lsp.prodstore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Warehouse extends AbstractStore {
    public boolean accept(Food food) {
        return food.getFreshLevel() < warehouse;
    }

    @Override
    public List<Food> selfCheck(Calendar baseDate) {
        List<Food> toBeMoved = new ArrayList<>();
        for (Food food : list) {
            Fresh.freshCalc(food, baseDate);
            if (!accept(food)) {
                toBeMoved.add(food);
            }
        }
        list.removeAll(toBeMoved);
        return toBeMoved;
    }
}
