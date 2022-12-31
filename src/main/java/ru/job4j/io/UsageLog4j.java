package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UsageLog4j {
private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

public  static void main(String[] args) {
    BasicConfigurator.configure();
    LOG.trace("trace message");
    LOG.debug("debug message");
    LOG.info("info message");
    LOG.warn("warn message");
    LOG.error("error message");

    String name = "Petr Arsentev";
    int age = 33;
    LOG.debug("User info : {}, age : {}", name, age);
    byte b = 127;
    short s = 32767;
    int i = 2147483647;
    long l = 2 ^ 63 - 1;
    float f = 1.1F;
    double d = 13D;
    boolean bln = true;
    char c = 13;
    LOG.info("8 primitives. \n"
            + "byte: {} \n"
            + "short: {} \n"
            + "int: {} \n"
            + "long: {} \n"
            + "float: {} \n"
            + "double: {} \n"
            + "boolean: {} \n"
            + "char: {} \n", b, s, i, l, f, d, bln, c);
    }
}