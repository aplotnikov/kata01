package org.home.kata01.product.amount;

import checkers.igj.quals.Mutable;

import javax.annotation.Nonnull;
import java.util.Objects;

@Mutable
public class MutableAmount implements Amount {
    protected int amount;

    @Nonnull
    public static Amount of(int value) {
        return new MutableAmount(value);
    }

    protected MutableAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int value() {
        return amount;
    }

    @Override
    public int compareTo(@Nonnull Amount other) {
        return Integer.compare(value(), other.value());
    }

    @Override
    public boolean isZero() {
        return amount == 0;
    }

    @Override
    public boolean isOne() {
        return amount == 1;
    }

    @Override
    public boolean isBigger(@Nonnull Amount other) {
        return value() > other.value();
    }

    @Override
    public void subtract(@Nonnull Amount subtrahend) {
        amount -= subtrahend.value();
    }

    @Override
    public void increase() {
        amount++;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof MutableAmount)) {
            return false;
        }

        MutableAmount that = (MutableAmount)other;
        return Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return String.format("Mutable %d amount", amount);
    }
}