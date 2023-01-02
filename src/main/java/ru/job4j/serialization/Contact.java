package ru.job4j.serialization;

import org.apache.log4j.BasicConfigurator;
import java.io.*;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Contact implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(Contact.class.getName());
    private static final long serialVersionUID = 1L;
    private String zipCode;
    private String phone;

    public Contact(String zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" + "zipCode=" + zipCode
                + ", phone='" + phone + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return zipCode == contact.zipCode && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, phone);
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        final Contact contact = new Contact("188653", "+7921666777");
        File locationFile = new File("./data/contact.data");
        try (FileOutputStream fos = new FileOutputStream(locationFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
             oos.writeObject(contact);
        } catch (IOException e) {
            LOG.error("IOException: ", e);
        }
        try (FileInputStream fis = new FileInputStream(locationFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
             Contact reincarnation = (Contact) ois.readObject();
             System.out.println(contact);
             System.out.println(reincarnation);
             System.out.println("Old VS New:  " + contact.equals(reincarnation));
        } catch (IOException e) {
            LOG.error("IOException: ", e);
        } catch (ClassNotFoundException f) {
            LOG.error("ClassNotFoundException: ", f);
        }
    }
}
