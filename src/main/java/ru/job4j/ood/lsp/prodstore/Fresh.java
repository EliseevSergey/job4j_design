package ru.job4j.ood.lsp.prodstore;

import java.util.Calendar;

public class Fresh {
    public static void freshCalc(Food food, Calendar baseDate) {
        float level;
        if (baseDate.before(food.getExpiryDate())) {
            level = (float) ((nanoGet(baseDate) - nanoGet(food.getCreateDate()))
                    / ((nanoGet(food.getExpiryDate()) - nanoGet(food.getCreateDate()))));
        } else {
            level = 1f;
        }
        food.setFreshLevel(level);
    }

    private static double nanoGet(Calendar calendar) {
        return calendar.getTime().getTime();
    }
}
