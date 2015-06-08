package org.home.kata01.product;

import org.home.kata01.product.amount.Amount;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.home.kata01.product.amount.Amount.Builder.anAmount;
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

        assertThat(price, is(equalTo(Price.of(20))));
    }

    @Test
    public void valueShouldBeMultipliedByValue() throws Exception {
        Amount multiplier = anAmount().withValue(10).create();

        price.multiply(multiplier);

        assertThat(price, is(equalTo(Price.of(100))));
    }

    @Test
    public void instanceShouldBeCreatedFromDoubleValue() throws Exception {
        Price actualPrice = Price.of(10);

        assertThat(actualPrice, is(notNullValue()));
        assertThat(actualPrice, is(equalTo(price)));
    }
}