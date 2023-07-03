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

class ReportCSVTest {
    @Test
    void whenCSV() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("IvanFirst", now, now, 89500);
        Employee worker2 = new Employee("IvanSecond", now, now, 70000);
        Employee worker3 = new Employee("IvanThird", now, now, 50000);
        store.add(worker3);
        store.add(worker);
        store.add(worker2);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();
        String delimiter = ";";

        ReportCSV reportCSV = new ReportCSV(store, parser, converter,
                Currency.RUB, Currency.USD, new DescComparator(), delimiter);

        StringBuilder expect = new StringBuilder()
                .append("Name;Hired;Fired;SalaryUSD")
                .append(System.lineSeparator())
                .append(worker.getName()).append(delimiter)
                .append(parser.parse(worker.getHired())).append(delimiter)
                .append(parser.parse(worker.getFired())).append(delimiter)
                .append(converter.convert(Currency.RUB, worker.getSalary(), Currency.USD))
                .append(System.lineSeparator())
                .append(worker2.getName()).append(delimiter)
                .append(parser.parse(worker2.getHired())).append(delimiter)
                .append(parser.parse(worker2.getFired())).append(delimiter)
                .append(converter.convert(Currency.RUB, worker2.getSalary(), Currency.USD))
                .append(System.lineSeparator())
                .append(worker3.getName()).append(delimiter)
                .append(parser.parse(worker3.getHired())).append(delimiter)
                .append(parser.parse(worker3.getFired())).append(delimiter)
                .append(converter.convert(Currency.RUB, worker3.getSalary(), Currency.USD))
                .append(System.lineSeparator());
        assertEquals(expect.toString(), reportCSV.generate(em -> true));
    }
}