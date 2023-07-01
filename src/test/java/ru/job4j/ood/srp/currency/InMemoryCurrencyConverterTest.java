package ru.job4j.ood.srp.currency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryCurrencyConverterTest {
    @Test
    public void whenRubUsd() {
        InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();
        double rsl = converter.convert(Currency.RUB, 100000, Currency.USD);
        double exp = 1100D;
        assertEquals(exp, rsl);
    }

}