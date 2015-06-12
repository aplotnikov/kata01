package org.home.kata01.utils;

import org.home.kata01.product.amount.Amount;
import org.home.kata01.product.amount.MutableAmount;

import javax.annotation.Nonnull;

public enum TestAmount {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FIVE(5);

    private final int value;

    TestAmount(int value) {
        this.value = value;
    }

    public @Nonnull Amount toAmount() {
        return MutableAmount.of(value);
    }

    public int toInt() {
        return value;
    }
}