package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportHR implements Report {
    private final Store store;
    private final Comparator<Employee> cmp;

    public ReportHR(Store store, Comparator<Employee> cmp) {
        this.store = store;
        this.cmp = cmp;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> employees = store.findBy(filter);
        employees.sort(cmp);
        for (Employee employee : employees) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}