package org.home.kata01.product;

import checkers.igj.quals.Immutable;

import javax.annotation.Nonnull;
import java.util.Objects;

@Immutable
public class Name {
    private final String name;

    @Nonnull
    public static Name of(@Nonnull String name) {
        return new Name(name);
    }

    private Name(@Nonnull String name) {
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

    @Override
    public String toString() {
        return name;
    }
}