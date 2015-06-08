package org.home.kata01.product.amount;

import javax.annotation.Nonnull;
import java.util.Objects;

public abstract class AbstractAmount implements Amount {
    protected int amount;

    protected AbstractAmount(int amount) {
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

        if (!(other instanceof AbstractAmount)) {
            return false;
        }

        AbstractAmount that = (AbstractAmount)other;
        return Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}