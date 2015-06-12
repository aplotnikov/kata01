package org.home.kata01.utils;

import org.home.kata01.product.Name;

import javax.annotation.Nonnull;

public enum TestName {
    A,
    B,
    C;

    public @Nonnull Name toName() {
        return Name.of(name());
    }
}