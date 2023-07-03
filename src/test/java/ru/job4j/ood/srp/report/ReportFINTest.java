package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportFINTest {
    @Test
    void whenRubToUsd() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 89500);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();
        store.add(worker);
        ReportFIN reportFIN = new ReportFIN(store, parser, converter,
                Currency.RUB, Currency.USD);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; SalaryUSD;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, worker.getSalary(), Currency.USD)).append(" ")
                .append(System.lineSeparator());
        assertEquals(expect.toString(), reportFIN.generate(em -> true));
    }
}