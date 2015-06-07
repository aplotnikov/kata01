package org.home.kata01.product.scanned;

import checkers.igj.quals.ReadOnly;

import org.home.kata01.product.Name;

import javax.annotation.Nonnull;

public class ScannedProduct {
    @ReadOnly
    public final Name name;
    public       int  amount;

    public ScannedProduct(@Nonnull String name) {
        this.name = Name.of(name);
        increaseAmount();
    }

    public void increaseAmount() {
        amount++;
    }
}