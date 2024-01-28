package ru.job4j.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NioDemo {
    public static void main(String[] args) {
        int count;
        try (SeekableByteChannel byteChannel = Files.newByteChannel(Paths.get("./data/Server.txt"))) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            do {
                count = byteChannel.read(byteBuffer);
                if (count != -1) {
                    byteBuffer.rewind();
                    for (int i = 0; i < count; i++) {
                        System.out.print((char) byteBuffer.get());

                    }
                }
            } while (count != -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
