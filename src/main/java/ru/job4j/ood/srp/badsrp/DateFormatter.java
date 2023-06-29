package ru.job4j.ood.srp.badsrp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}
