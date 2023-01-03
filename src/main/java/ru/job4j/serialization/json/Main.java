package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Person person = new Person(false, 30,
                new Contact("188653", "8921666777"), new String[]{"Worker", "Married"});
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println("marshalled into XML: \n" + xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Person resurrection = (Person) unmarshaller.unmarshal(reader);
            System.out.println("Unmarshalled from XML: \n" + resurrection);
        }

        Gson gson = new GsonBuilder().create();
        System.out.println("Inputted in Json: \n" + gson.toJson(person));
        String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println("Extracted from Json: \n" + personMod);

        JSONObject jsonContact = new JSONObject("{\"zipCode\":\"188653\", \"phone\":\"8921666777888\"}");
        List<String> list = List.of("Student", "Free");
        JSONArray jsonStatuses = new JSONArray(list);

        final Person prsn = new Person(false, 30, new Contact("188653", "11-111"), new String[]{"Worker", "Married"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", prsn.isSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        System.out.println("Made directly jsonObject: \n" + jsonObject);
    }
}


