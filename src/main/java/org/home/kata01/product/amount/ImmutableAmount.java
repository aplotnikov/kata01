package org.home.kata01.product.amount;

import checkers.igj.quals.Immutable;

import javax.annotation.Nonnull;

@Immutable
public class ImmutableAmount extends MutableAmount {
    @Nonnull
    public static Amount of(int value) {
        return new ImmutableAmount(value);
    }

    private ImmutableAmount(int amount) {
        super(amount);
    }

    @Override
    public void increase() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void subtract(@Nonnull Amount subtrahend) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return String.format("Immutable %d amount", amount);
    }
}