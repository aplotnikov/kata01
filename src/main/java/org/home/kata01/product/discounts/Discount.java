package org.home.kata01.product.discounts;

import org.home.kata01.product.Price;

import javax.annotation.Nonnull;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.nonNull;

public class Discount {
    public final int   amount;
    public final Price price;

    private Discount(int amount, @Nonnull Price price) {
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

    public static class Builder {
        private int   amount;
        private Price price;

        private Builder() {
        }

        @Nonnull
        public static Builder aDiscount() {
            return new Builder();
        }

        @Nonnull
        public Builder withProductAmount(int amount) {
            this.amount = amount;
            return this;
        }

        @Nonnull
        public Builder withPrice(@Nonnull Price price) {
            this.price = price;
            return this;
        }

        public @Nonnull Discount create() {
            checkState(amount > 0, "'product amount' parameter has to be bigger than zero.");
            checkState(nonNull(price), "'price' parameter has to be initialized.");

            return new Discount(amount, price);
        }
    }
}