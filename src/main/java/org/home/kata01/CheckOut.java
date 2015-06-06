package org.home.kata01;

import org.home.kata01.product.Name;
import org.home.kata01.product.Product;
import org.home.kata01.product.ScannedProduct;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CheckOut {
    private final Map<Name, Product>          products;
    private final Map<String, ScannedProduct> scannedProducts;

    private CheckOut(@Nonnull Map<Name, Product> products) {
        this.products = products;
        scannedProducts = new HashMap<>();
    }

    public void scan(@Nonnull String name) {
        scannedProducts.computeIfPresent(name, (existedName, scannedProduct) -> {
                                             scannedProduct.increaseAmount();
                                             return scannedProduct;
                                         }
                                        );
        scannedProducts.putIfAbsent(name, new ScannedProduct(name));
    }

    @Nonnull
    public BigDecimal getPrice() {
        BigDecimal price = BigDecimal.ZERO;

        for (ScannedProduct scannedProduct : scannedProducts.values()) {
            Product currentProduct = products.get(scannedProduct.name);
            BigDecimal productPrice = currentProduct.price.multiply(BigDecimal.valueOf(scannedProduct.amount));
            price = price.add(productPrice);
        }

        return price;
    }

    public static class Builder {
        private final Map<Name, Product> products;

        private Builder() {
            products = new HashMap<>();
        }

        @Nonnull
        public static Builder aCheckOut() {
            return new Builder();
        }

        @Nonnull
        public CheckOut build() {
            return new CheckOut(products);
        }

        @Nonnull
        public Builder withProduct(@Nonnull Product product) throws IllegalStateException {
            products.computeIfPresent(product.name, (name, existedProduct) -> {
                throw new IllegalStateException("The product with \'" + name + "\' name is already existed.");
            });

            products.put(product.name, product);
            return this;
        }
    }
}