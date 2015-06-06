package org.home.kata01.product;

import javax.annotation.Nonnull;

public class ScannedProduct {
    public final Name name;
    public       int  amount;

    public ScannedProduct(@Nonnull String name) {
        this.name = new Name(name);
        increaseAmount();
    }

    public void increaseAmount() {
        amount++;
    }
}