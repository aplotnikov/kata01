package org.home.kata01.product.discounts;

import checkers.igj.quals.Immutable;

import org.home.kata01.product.Price;
import org.home.kata01.product.amount.Amount;

import javax.annotation.Nonnull;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkState;
import static org.home.kata01.product.amount.Amount.Builder.anAmount;

@Immutable
public class Discount {
    public final Amount amount;
    public final Price  price;

    private Discount(@Nonnull Amount amount, @Nonnull Price price) {
        this.amount = amount;
        this.price = price;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Discount)) {
            return false;
        }

        Discount discount = (Discount)other;
        return Objects.equals(amount, discount.amount) && Objects.equals(price, discount.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, price);
    }

    @Override
    public String toString() {
        return String.format("Discount for %d products with price %s", amount.value(), price);
    }

    public static class Builder {
        private int    amount;
        private double price;

        private Builder() {
        }

        @Nonnull
        public static Builder aDiscount() {
            return new Builder();
        }

        @Nonnull
        public Builder forProductAmount(int amount) {
            this.amount = amount;
            return this;
        }

        @Nonnull
        public Builder withPrice(double price) {
            this.price = price;
            return this;
        }

        @Nonnull
        public Discount create() {
            checkState(amount > 0, "'product amount' parameter has to be bigger than zero.");
            checkState(price >= 0, "'price' parameter has to be not less than zero.");

            return new Discount(anAmount().withValue(amount).create(),
                                Price.of(price));
        }
    }
}