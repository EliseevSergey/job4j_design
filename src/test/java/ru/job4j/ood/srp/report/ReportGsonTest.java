package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class ReportGsonTest {

    @Test
    void whenGson() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 89500);
        Employee worker2 = new Employee("IvanSecond", now, now, 10000);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker2);
        ReportGson reportGson = new ReportGson(store, parser);
        String expect = String.format("[{\"name\":\"Ivan\",\"hired\":\"%s\",\"fired\":\"%s\",\"salary\":89500.0},"
                        + "{\"name\":\"IvanSecond\",\"hired\":\"%s\",\"fired\":\"%s\",\"salary\":10000.0}]",
                parser.parse(worker.getHired()), parser.parse(worker.getFired()),
                parser.parse(worker2.getHired()), parser.parse(worker2.getFired()));
        assertEquals(expect.toString(), reportGson.generate(em -> true));
    }
}