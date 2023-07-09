package ru.job4j.ood.lsp.prodstore;

import java.util.Calendar;
import java.util.List;

public class ControlQuality {
    private List<Store> storages;

    public ControlQuality(List<Store> storages) {
        this.storages = storages;
    }

    public static float fresh(Food food) {
        float level;
        if (Calendar.getInstance().before(food.getExpiryDate())) {
            level = (float) ((nanoGet(Calendar.getInstance()) - nanoGet(food.getCreateDate()))
                    / ((nanoGet(food.getExpiryDate()) - nanoGet(food.getCreateDate()))));
        } else {
            level = 1f;
        }
        return level;
    }

    public void distribute(Food food) {
        for (Store st : storages) {
            if (st.accept(food)) {
                st.add(food);
                break;
            }
        }
    }

    private static double nanoGet(Calendar calendar) {
        return calendar.getTime().getTime();
    }
}
