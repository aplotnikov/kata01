package org.home.kata01.product;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductsManager {
    private final Map<Name, Product> products;

    public ProductsManager() {
        this.products = new HashMap<>();
    }

    public void addProduct(@Nonnull Product product) throws IllegalStateException {
        products.computeIfPresent(product.name, (name, existedProduct) -> {
            throw new IllegalStateException("The product with \'" + name + "\' name is already existed.");
        });

        products.put(product.name, product);
    }

    @Nonnull
    public Optional<Product> findProductByName(@Nonnull Name name) {
        return Optional.ofNullable(products.get(name));
    }
}