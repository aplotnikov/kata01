package org.home.kata01.product;

import org.jmlspecs.annotation.Readonly;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.nonNull;

public class Product {
    @Readonly
    public final Name       name;
    @Readonly
    public final BigDecimal price;

    private Product(@Nonnull String name, @Nonnull BigDecimal price) {
        this.name = new Name(name);
        this.price = price;
    }

    public static class Builder {
        private String     name;
        private BigDecimal price;

        private Builder() {
        }

        @Nonnull
        public static Builder aProduct() {
            return new Builder();
        }

        @Nonnull
        public Builder withName(@Nonnull String name) {
            this.name = name;
            return this;
        }

        @Nonnull
        public Builder withPrice(@Nonnull BigDecimal price) {
            this.price = price;
            return this;
        }

        @Nonnull
        public Product build() {
            checkState(nonNull(name), "'name' parameter has to be initialized.");
            checkState(nonNull(price), "'price' parameter has to be initialized.");

            return new Product(name, price);
        }
    }
}