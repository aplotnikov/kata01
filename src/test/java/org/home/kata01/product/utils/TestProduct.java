package org.home.kata01.product.utils;

import org.home.kata01.product.Product;

import javax.annotation.Nonnull;

import static org.home.kata01.product.Product.Builder.aProduct;

public enum TestProduct {
    FIRST(TestName.FIRST, TestPrice.TEN),
    SECOND(TestName.SECOND, TestPrice.FIVE);

    private final TestName  name;
    private final TestPrice price;

    TestProduct(@Nonnull TestName name, @Nonnull TestPrice price) {
        this.name = name;
        this.price = price;
    }

    public @Nonnull Product toProduct() {
        return aProduct().withName(name.name())
                         .withPrice(price.getValue())
                         .create();
    }
}