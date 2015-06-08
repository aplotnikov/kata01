package org.home.kata01.product.amount;

import javax.annotation.Nonnull;

public interface Amount extends Comparable<Amount> {

    int value();

    void increase();

    void subtract(@Nonnull Amount subtrahend);

    boolean isZero();

    boolean isOne();

    boolean isBigger(@Nonnull Amount other);

    class Builder {
        private boolean isMutable;
        private int     value;

        @Nonnull
        public static Builder anAmount() {
            return new Builder();
        }

        @Nonnull
        public Builder withValue(int value) {
            this.value = value;
            return this;
        }

        @Nonnull
        public Builder isMutable() {
            isMutable = true;
            return this;
        }

        @Nonnull
        public Amount create() {
            if (isMutable) {
                return MutableAmount.of(value);
            }

            return ImmutableAmount.of(value);
        }
    }
}