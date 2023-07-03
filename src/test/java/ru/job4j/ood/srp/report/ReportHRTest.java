package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

class ReportHRTest {
    @Test
    void whenDescendingOrder() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("IvanFirst", now, now, 89500);
        Employee worker2 = new Employee("IvanSecond", now, now, 70000);
        Employee worker3 = new Employee("IvanThird", now, now, 50000);
        store.add(worker3);
        store.add(worker);
        store.add(worker2);
        ReportHR reportHR = new ReportHR(store, new DescComparator());
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker3.getName()).append(" ")
                .append(worker3.getSalary())
                .append(System.lineSeparator());
        assertEquals(expect.toString(), reportHR.generate(em -> true));
    }
}