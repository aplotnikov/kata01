package org.home.kata01.product.amount;

import checkers.igj.quals.Mutable;

import javax.annotation.Nonnull;

@Mutable
public class MutableAmount extends AbstractAmount {
    @Nonnull
    public static Amount of(int value) {
        return new MutableAmount(value);
    }

    private MutableAmount(int amount) {
        super(amount);
    }

    @Override
    public String toString() {
        return "Mutable " + amount + " amount";
    }
}