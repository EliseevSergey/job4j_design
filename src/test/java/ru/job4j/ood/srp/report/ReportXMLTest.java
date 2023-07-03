package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.TestDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.assertEquals;
class ReportXMLTest {

    @Test
    void whenXML() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 89500);
        Employee worker2 = new Employee("IvanSecond", now, now, 10000);
        DateTimeParser<Calendar> parser = new TestDateTimeParser();
        store.add(worker);
        store.add(worker2);
        ReportXML reportXML = new ReportXML(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employees>\n")
                .append("    <employee>\n")
                .append(String.format("        <name>%s</name>\n", worker.getName()))
                .append(String.format("        <hired>%s</hired>\n", parser.parse(worker.getHired())))
                .append(String.format("        <fired>%s</fired>\n", parser.parse(worker.getHired())))
                .append(String.format("        <salary>%s</salary>\n", worker.getSalary()))
                .append("    </employee>\n")
                .append("    <employee>\n")
                .append(String.format("        <name>%s</name>\n", worker2.getName()))
                .append(String.format("        <hired>%s</hired>\n", parser.parse(worker2.getHired())))
                .append(String.format("        <fired>%s</fired>\n", parser.parse(worker2.getHired())))
                .append(String.format("        <salary>%s</salary>\n", worker2.getSalary()))
                .append("    </employee>\n")
                .append("</employees>\n");
        assertEquals(expect.toString(), reportXML.generate(em -> true));
    }
}