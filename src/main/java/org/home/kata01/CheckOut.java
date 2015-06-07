package org.home.kata01;

import org.home.kata01.product.Price;
import org.home.kata01.product.Product;
import org.home.kata01.product.ProductsManager;
import org.home.kata01.product.scanned.ScannedProductsKeeper;

import javax.annotation.Nonnull;

public class CheckOut {
    private final ProductsManager       productsManager;
    private final ScannedProductsKeeper scannedProductsKeeper;

    private CheckOut(@Nonnull ProductsManager productsManager) {
        this.productsManager = productsManager;
        scannedProductsKeeper = new ScannedProductsKeeper();
    }

    public void scan(@Nonnull String name) {
        scannedProductsKeeper.addScannedProduct(name);
    }

    @Nonnull
    public Price getPrice() {
        final Price price = Price.zero();
        scannedProductsKeeper.iterateProducts(
                scannedProduct ->
                        productsManager.findProductByName(scannedProduct.name).ifPresent(currentProduct -> {
                            Price productPrice = currentProduct.getPriceForAmount(scannedProduct.amount);
                            price.add(productPrice);
                        }));
        return price;
    }

    public static class Builder {
        private final ProductsManager productsManager;

        private Builder() {
            productsManager = new ProductsManager();
        }

        @Nonnull
        public static Builder aCheckOut() {
            return new Builder();
        }

        @Nonnull
        public CheckOut create() {
            return new CheckOut(productsManager);
        }

        @Nonnull
        public Builder withProduct(@Nonnull Product product) {
            productsManager.addProduct(product);
            return this;
        }
    }
}