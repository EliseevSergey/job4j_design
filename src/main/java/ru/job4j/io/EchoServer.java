package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                     String clientReqFirstLine = in.readLine();

                    if (clientReqFirstLine.contains("msg=Hello")) {
                        out.write("HTTP/1.1 200 OK \r\n\r\n".getBytes());
                        out.write("Hello. ".getBytes());
                    }

                    if (clientReqFirstLine.contains("msg=Exit")) {
                        out.write("HTTP/1.1 200 OK \r\n\r\n".getBytes());
                        out.write("Bye. ".getBytes());
                        server.close();
                    }
                    if (!clientReqFirstLine.contains("msg=Exit") && !clientReqFirstLine.contains("msg=Hello")) {
                        out.write("HTTP/1.1 200 OK \r\n\r\n".getBytes());
                        out.write("What? ".getBytes());
                    }

                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("IOException: ", e);
        }
    }
}

