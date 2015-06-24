package org.home.kata01.utils;

import org.home.kata01.product.Product;

import javax.annotation.Nonnull;

import static org.home.kata01.product.Product.Builder.aProduct;

public enum TestProduct {
    A(TestName.A, TestPrice.TEN),
    B(TestName.B, TestPrice.FIVE),
    C(TestName.C, TestPrice.TWENTY);

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