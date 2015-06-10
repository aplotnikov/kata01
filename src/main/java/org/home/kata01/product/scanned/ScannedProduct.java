package org.home.kata01.product.scanned;

import checkers.igj.quals.Mutable;

import org.home.kata01.product.Name;
import org.home.kata01.product.amount.Amount;

import javax.annotation.Nonnull;
import java.util.Objects;

import static org.home.kata01.product.amount.Amount.Builder.anAmount;

@Mutable
public class ScannedProduct {
    public final Name   name;
    public final Amount amount;

    @Nonnull
    public static ScannedProduct of(@Nonnull String name) {
        return new ScannedProduct(name);
    }

    private ScannedProduct(@Nonnull String name) {
        this.name = Name.of(name);
        amount = anAmount().withValue(1).isMutable().create();
    }

    public void increaseAmount() {
        amount.increase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ScannedProduct)) {
            return false;
        }

        ScannedProduct that = (ScannedProduct)o;
        return Objects.equals(name, that.name) &&
               Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount);
    }

    @Override
    public String toString() {
        return String.format("Product \'%s\' is scanned %d times", name, amount.value());
    }
}