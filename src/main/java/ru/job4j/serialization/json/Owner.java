package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "catowner")
@XmlAccessorType(XmlAccessType.FIELD)

public class Owner {
    @XmlAttribute
    private String surname;
    @XmlElementWrapper(name = "cats")
    @XmlElement(name = "cat")
    private Cat[] pets;
    @XmlAttribute
    private int age;
    @XmlAttribute
    boolean vip;

    public Owner() {
    }

    public Owner(String surname, Cat[] pets, int age, boolean vip) {
        this.surname = surname;
        this.pets = pets;
        this.age = age;
        this.vip = vip;
    }

    public String getSurname() {
        return surname;
    }

    public Cat[] getPets() {
        return pets;
    }

    public int getAge() {
        return age;
    }

    public boolean isVip() {
        return vip;
    }

    @Override
    public String toString() {
        return "Owner{"
                + "surname='" + surname + '\''
                + ", pets=" + Arrays.toString(pets)
                + ", age=" + age
                + ", vip=" + vip
                + '}';
    }

    public static void main(String[] args) throws Exception {
        Owner petrov = new Owner("Petrov",
                new Cat[]{new Cat("Barsik", 2, "Grey"),
                        new Cat("Vasya", 1, "Black")}, 33, true);
        Gson gson = new GsonBuilder().create();
        String gsonedPetrov = gson.toJson(petrov);
        System.out.println(gsonedPetrov);

        String ownerJson = "{\"surname\":\"Sidorov\",\"pets\":[{\"name\":\"Marsik\",\"age\":2,\"color\":\"Grey\"},"
                + "{\"name\":\"Pushok\",\"age\":1,\"color\":\"White\"}],\"age\":33,\"vip\":true}";
        Owner fromJsonString = gson.fromJson(ownerJson, Owner.class);
        System.out.println(fromJsonString);

        JAXBContext context = JAXBContext.newInstance(Owner.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xmlPetrov = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(petrov, writer);
            xmlPetrov = writer.getBuffer().toString();
            System.out.println(xmlPetrov);
        }

        JSONObject jsonObjectSidorov = new JSONObject();
        jsonObjectSidorov.put("surname", "Sidorov");
        JSONArray jsonArrayCats = new JSONArray("[{\"name\":\"Marsik\",\"age\":2,\"color\":\"Grey\"}, {\"name\":\"Pushok\",\"age\":1,\"color\":\"White\"}]");
        jsonObjectSidorov.put("pets", jsonArrayCats);
        jsonObjectSidorov.put("age", 33);
        jsonObjectSidorov.put("vip", true);

        System.out.println("Made directly jsonObject: \n" + jsonObjectSidorov);
    }
}