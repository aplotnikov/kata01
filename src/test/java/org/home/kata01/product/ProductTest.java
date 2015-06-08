package org.home.kata01.product;

import org.home.kata01.product.discounts.Discount;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.home.kata01.product.Product.Builder.aProduct;
import static org.home.kata01.product.discounts.Discount.Builder.aDiscount;
import static org.junit.Assert.assertThat;

public class ProductTest {
    private static final String NAME = "name";

    @Test(expected = IllegalStateException.class)
    public void exceptionShouldBeThrownWhenNameParameterIsEmpty() throws Exception {
        aProduct().create();
    }

    @Test(expected = IllegalStateException.class)
    public void exceptionShouldBeThrownWhenPriceParameterIsEmpty() throws Exception {
        aProduct().withName(NAME).create();
    }

    @Test(expected = IllegalStateException.class)
    public void exceptionShouldBeThrownWhenRuleWithTheSameAmountOfProductIsAlreadyExisted() throws Exception {
        Discount discount = aDiscount().forProductAmount(2).withPrice(10).create();

        aProduct().withDiscount(discount).withDiscount(discount).create();
    }

    @Test
    public void instanceShouldBeCreated() throws Exception {
        Product product = aProduct().withName(NAME).withPrice(10).create();

        assertThat(product, is(notNullValue()));
        assertThat(product.name, is(equalTo(Name.of(NAME))));
        assertThat(product.price, is(equalTo(Price.of(10))));
    }
}