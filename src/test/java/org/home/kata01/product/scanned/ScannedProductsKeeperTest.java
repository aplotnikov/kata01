package org.home.kata01.product.scanned;

import org.home.kata01.Products;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class ScannedProductsKeeperTest {
    @Test
    public void shouldBeAbleToIterateDifferentItems() throws Exception {
        ScannedProductsKeeper keeper = new ScannedProductsKeeper();

        keeper.addScannedProduct(Products.A.getName());
        keeper.addScannedProduct(Products.A.getName());
        keeper.addScannedProduct(Products.B.getName());

        List<ScannedProduct> scannedProducts = new ArrayList<>();
        keeper.iterateProducts(scannedProducts::add);

        ScannedProduct scannedProductA = ScannedProduct.of(Products.A.getName());
        scannedProductA.increaseAmount();
        ScannedProduct scannedProductB = ScannedProduct.of(Products.B.getName());

        assertThat(scannedProducts, both(hasItem(equalTo(scannedProductA))).and(hasItem(equalTo(scannedProductB))));
    }
}