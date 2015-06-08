package org.home.kata01.product;

import org.home.kata01.product.amount.Amount;
import org.home.kata01.product.discounts.Discount;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.home.kata01.product.amount.Amount.Builder.anAmount;
import static org.home.kata01.product.discounts.Discount.Builder.aDiscount;
import static org.junit.Assert.assertThat;

public class DiscountTest {
    @Test(expected = IllegalStateException.class)
    public void exceptionShouldBeThrownWhenAmountParameterIsLessThanZero() throws Exception {
        aDiscount().forProductAmount(-1).create();
    }

    @Test(expected = IllegalStateException.class)
    public void exceptionShouldBeThrownWhenPriceParameterIsEmpty() throws Exception {
        aDiscount().withPrice(10).create();
    }

    @Test
    public void instanceShouldBeCreated() throws Exception {
        Amount expectedAmount = anAmount().withValue(5).create();
        Price expectedPrice = Price.of(10);

        Discount product = aDiscount().forProductAmount(5).withPrice(10).create();

        assertThat(product, is(notNullValue()));
        assertThat(product.amount, is(equalTo(expectedAmount)));
        assertThat(product.price, is(equalTo(expectedPrice)));
    }
}