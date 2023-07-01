package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportCSV implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final InMemoryCurrencyConverter converter;
    private final Currency source;
    private final Currency target;
    private final Comparator<Employee> cmp;
    private final String delimiter;

    public ReportCSV(Store store, DateTimeParser<Calendar> dateTimeParser,
                     InMemoryCurrencyConverter converter, Currency source, Currency target, Comparator<Employee> cmp, String delimiter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.converter = converter;
        this.source = source;
        this.target = target;
        this.cmp = cmp;
        this.delimiter = delimiter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(String.format("Name%sHired%sFired%sSalary%S", delimiter, delimiter, delimiter, target))
                .append(System.lineSeparator());
        List<Employee> employees = store.findBy(filter);
        employees.sort(cmp);
        for (Employee employee : employees) {
            text.append(employee.getName()).append(delimiter)
                    .append(dateTimeParser.parse(employee.getHired())).append(delimiter)
                    .append(dateTimeParser.parse(employee.getFired())).append(delimiter)
                    .append(converter.convert(source, employee.getSalary(), target))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}