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
    @Test(expected = IllegalStateException.class)
    public void exceptionShouldBeThrownWhenNameParameterIsEmpty() throws Exception {
        aProduct().create();
    }

    @Test(expected = IllegalStateException.class)
    public void exceptionShouldBeThrownWhenPriceParameterIsEmpty() throws Exception {
        aProduct().withName("name").create();
    }

    @Test(expected = IllegalStateException.class)
    public void exceptionShouldBeThrownWhenRuleWithTheSameAmountOfProductIsAlreadyExisted() throws Exception {
        Discount discount = aDiscount().withProductAmount(2).withPrice(Price.of(10)).create();

        aProduct().withRule(discount).withRule(discount).create();
    }

    @Test
    public void instanceShouldBeCreated() throws Exception {
        String name = "name";
        Price price = Price.of(10);

        Product product = aProduct().withName(name).withPrice(price).create();

        assertThat(product, is(notNullValue()));
        assertThat(product.name, is(equalTo(Name.of(name))));
        assertThat(product.price, is(equalTo(price)));
    }
}