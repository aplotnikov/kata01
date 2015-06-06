package org.home.kata01.product;

import javax.annotation.Nonnull;
import java.util.Objects;

public class Name {
    private final String name;

    public Name(@Nonnull String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Name)) {
            return false;
        }

        Name otherInstance = (Name)other;
        return Objects.equals(name, otherInstance.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}