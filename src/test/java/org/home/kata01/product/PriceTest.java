package org.home.kata01.product;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class PriceTest {
    private Price price;

    @Before
    public void setUp() throws Exception {
        price = Price.of(10);
    }

    @Test
    public void valueShouldBeAddedToPrice() throws Exception {
        Price otherPrice = Price.of(10);

        price.add(otherPrice);

        assertThat(price, is(equalTo(Price.of(20d))));
    }

    @Test
    public void valueShouldBeMultipliedByValue() throws Exception {
        price.multiply(10);

        assertThat(price, is(equalTo(Price.of(100))));
    }

    @Test
    public void instanceShouldBeCreatedFromDoubleValue() throws Exception {
        Price price = Price.of(10);

        assertThat(price, is(notNullValue()));
        assertThat(price, is(equalTo(Price.of(10))));
    }
}