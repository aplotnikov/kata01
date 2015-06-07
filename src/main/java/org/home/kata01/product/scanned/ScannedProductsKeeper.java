package org.home.kata01.product.scanned;

import org.home.kata01.product.Name;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ScannedProductsKeeper {
    private final Map<Name, ScannedProduct> scannedProducts;

    public ScannedProductsKeeper() {
        scannedProducts = new HashMap<>();
    }

    public void addScannedProduct(@Nonnull String name) {
        Name productName = Name.of(name);
        scannedProducts.computeIfPresent(productName, (existedName, scannedProduct) -> {
            scannedProduct.increaseAmount();
            return scannedProduct;
        });
        scannedProducts.putIfAbsent(productName, ScannedProduct.of(name));
    }

    public void iterateProducts(@Nonnull Consumer<ScannedProduct> productAnalyzer) {
        scannedProducts.values().stream().forEach(productAnalyzer);
    }
}