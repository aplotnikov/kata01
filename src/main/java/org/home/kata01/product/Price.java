package org.home.kata01.product;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.Objects;

public class Price {
    private BigDecimal value;

    @Nonnull
    public static Price zero() {
        return new Price();
    }

    private Price() {
        value = BigDecimal.ZERO;
    }

    @Nonnull
    public static Price of(double value) {
        return new Price(value);
    }

    private Price(double value) {
        this.value = BigDecimal.valueOf(value);
    }

    @Nonnull
    public Price add(@Nonnull Price otherPrice) {
        value = value.add(otherPrice.value);
        return this;
    }

    @Nonnull
    public Price multiply(int otherValue) {
        value = value.multiply(BigDecimal.valueOf(otherValue));
        return this;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Price)) {
            return false;
        }

        Price price = (Price)other;
        return Objects.equals(value, price.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}