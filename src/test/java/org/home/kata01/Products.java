package org.home.kata01;

import org.home.kata01.product.Price;
import org.home.kata01.product.Product;

import javax.annotation.Nonnull;

import static org.home.kata01.product.Product.Builder.aProduct;

public enum Products {
    A("A", 10),
    B("B", 20),
    C("C", 30);

    private final String name;
    private final Price  price;

    Products(@Nonnull String name, double price) {
        this.name = name;
        this.price = Price.of(price);
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public Price getPrice() {
        return price;
    }

    @Nonnull
    public Product toProduct() {
        return aProduct().withName(name)
                         .withPrice(price)
                         .create();
    }
}