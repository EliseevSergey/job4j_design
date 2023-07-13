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
        System.out.printf("%s, FreshCalc has been called: %s %n", food.getName(), food.getFreshLevel());
    }

    private static double nanoGet(Calendar calendar) {
        return calendar.getTime().getTime();
    }
}
