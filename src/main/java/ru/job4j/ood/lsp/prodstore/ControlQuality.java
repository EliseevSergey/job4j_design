package ru.job4j.ood.lsp.prodstore;

import java.util.ArrayList;
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

    public void resort(Calendar baseDate) {
        List<Food> rejectedFromAllStores = new ArrayList<>();
        for (Store st : storages) {
            rejectedFromAllStores.addAll(st.selfCheck(baseDate));
        }
        distribute(rejectedFromAllStores, baseDate);
    }
}

