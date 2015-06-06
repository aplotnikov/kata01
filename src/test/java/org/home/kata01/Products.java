package org.home.kata01;

import org.home.kata01.product.Product;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

import static org.home.kata01.product.Product.Builder.aProduct;

public enum Products {
    A("A", BigDecimal.TEN),
    B("B", BigDecimal.valueOf(20)),
    C("C", BigDecimal.valueOf(30));

    private final String     name;
    private final BigDecimal price;

    Products(@Nonnull String name, @Nonnull BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public BigDecimal getPrice() {
        return price;
    }

    @Nonnull
    public Product toProduct() {
        return aProduct().withName(name).withPrice(price).build();
    }
}