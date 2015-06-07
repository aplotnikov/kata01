package org.home.kata01.product.scanned;

import checkers.igj.quals.ReadOnly;

import org.home.kata01.product.Name;

import javax.annotation.Nonnull;

public class ScannedProduct {
    @ReadOnly
    public final Name name;
    public       int  amount;

    @Nonnull
    public static ScannedProduct of(@Nonnull String name) {
        return new ScannedProduct(name);
    }

    private ScannedProduct(@Nonnull String name) {
        this.name = Name.of(name);
        // TODO if ammount is class what about this places
        increaseAmount();
    }

    public void increaseAmount() {
        amount++;
    }
}