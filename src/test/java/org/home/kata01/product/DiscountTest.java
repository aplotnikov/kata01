package org.home.kata01.product;

import org.home.kata01.product.discounts.Discount;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.home.kata01.product.discounts.Discount.Builder.aDiscount;
import static org.junit.Assert.assertThat;

public class DiscountTest {
    @Test(expected = IllegalStateException.class)
    public void exceptionShouldBeThrownWhenAmountParameterIsLessThanZero() throws Exception {
        aDiscount().withProductAmount(-1).create();
    }

    @Test(expected = IllegalStateException.class)
    public void exceptionShouldBeThrownWhenPriceParameterIsEmpty() throws Exception {
        aDiscount().withPrice(Price.of(10)).create();
    }

    @Test
    public void instanceShouldBeCreated() throws Exception {
        int amount = 5;
        Price price = Price.of(10);

        Discount product = aDiscount().withProductAmount(amount).withPrice(price).create();

        assertThat(product, is(notNullValue()));
        assertThat(product.amount, is(amount));
        assertThat(product.price, is(price));
    }
}