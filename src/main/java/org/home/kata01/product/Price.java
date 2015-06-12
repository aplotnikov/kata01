package org.home.kata01.product;

import checkers.igj.quals.Mutable;

import org.home.kata01.product.amount.Amount;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.Objects;

@Mutable
public class Price {
    private BigDecimal value;

    @Nonnull
    public static Price zero() {
        return Price.of(0);
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
    public Price multiply(@Nonnull Amount otherValue) {
        BigDecimal multiplier = BigDecimal.valueOf(otherValue.value());
        value = value.multiply(multiplier);
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

    @Override
    public String toString() {
        return value.toString();
    }
}