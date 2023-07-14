package ru.job4j.ood.lsp.prodstore;

import java.util.Calendar;
import java.util.List;

public class ControlQuality {
    private List<Store> storages;

    public ControlQuality(List<Store> storages) {
        this.storages = storages;
    }

    public void distribute(List<Food> goods, Calendar baseDate) {
        for (Food food : goods) {
            Fresh.freshCalc(food, baseDate);
            for (Store st : storages) {
                if (st.accept(food)) {
                    st.add(food);
                    break;
                }
            }
        }
    }
}

