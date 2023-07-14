package ru.job4j.ood.lsp.prodstore;

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
}
