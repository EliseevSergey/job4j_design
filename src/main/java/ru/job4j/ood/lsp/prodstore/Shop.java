package ru.job4j.ood.lsp.prodstore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Shop extends AbstractStore {
    @Override
    public boolean accept(Food food) {
        boolean rsl = false;
        float fresh = food.getFreshLevel();
        if ((fresh >= warehouse) && (fresh < trash)) {
            rsl = true;
            if (fresh >= shopDiscount) {
                food.setPrice(food.getPrice() * (100 - food.getDiscount()) / 100);
            }
        }
        return rsl;
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
