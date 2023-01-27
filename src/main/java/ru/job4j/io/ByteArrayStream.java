package ru.job4j.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayStream {
    public static void main(String[] args) {
        byte[] bytes = new byte[]{33, 'j', 'a', 'v', 'a'};
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes, 2, 3);
        int data;
        while ((data = bais.read()) != -1) {
            System.out.println(String.format("byte %s = simbol %s", data, (char) data));
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes2 = "Message".getBytes();
        baos.writeBytes(bytes2);
        byte[] byteReload = baos.toByteArray();
        System.out.println(baos);

        try (FileOutputStream fos = new FileOutputStream("./data/forByteArray")) {
            baos.writeTo(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
