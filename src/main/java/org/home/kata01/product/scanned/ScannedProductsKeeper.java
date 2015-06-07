package org.home.kata01.product.scanned;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ScannedProductsKeeper {
    private final Map<String, ScannedProduct> scannedProducts;

    public ScannedProductsKeeper() {
        scannedProducts = new HashMap<>();
    }

    public void addScannedProduct(@Nonnull String name) {
        scannedProducts.computeIfPresent(name, (existedName, scannedProduct) -> {
            scannedProduct.increaseAmount();
            return scannedProduct;
        });
        scannedProducts.putIfAbsent(name, new ScannedProduct(name));
    }

    public void iterateProducts(@Nonnull Consumer<ScannedProduct> productAnalyzer) {
        scannedProducts.values().stream().forEach(productAnalyzer);
    }
}