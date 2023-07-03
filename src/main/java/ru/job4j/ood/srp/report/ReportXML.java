package ru.job4j.ood.srp.report;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.function.Predicate;

public class ReportXML implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private JAXBContext context;
    private Marshaller marshaller;

    public ReportXML(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        try {
            context = JAXBContext.newInstance(MemStore.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException je) {
            je.printStackTrace();
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
            String xml = "";
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(store, writer);
                xml = writer.getBuffer().toString();
            } catch (IOException | JAXBException e) {
                e.printStackTrace();
            }
        return xml;
    }
}