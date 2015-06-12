package org.home.kata01.product.utils;

import org.home.kata01.product.Name;

import javax.annotation.Nonnull;

public enum TestName {
    FIRST,
    SECOND;

    public @Nonnull Name toName() {
        return Name.of(name());
    }
}