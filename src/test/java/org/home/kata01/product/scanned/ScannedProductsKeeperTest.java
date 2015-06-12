package org.home.kata01.product.scanned;

import org.home.kata01.utils.TestProduct;
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

        String firstProductName = TestProduct.A.toProduct().name.toString();
        String secondProductName = TestProduct.B.toProduct().name.toString();

        keeper.addScannedProduct(firstProductName);
        keeper.addScannedProduct(secondProductName);
        keeper.addScannedProduct(firstProductName);

        List<ScannedProduct> scannedProducts = new ArrayList<>();
        keeper.iterateProducts(scannedProducts::add);

        ScannedProduct scannedProductA = ScannedProduct.of(firstProductName);
        scannedProductA.increaseAmount();
        ScannedProduct scannedProductB = ScannedProduct.of(secondProductName);

        assertThat(scannedProducts, both(hasItem(equalTo(scannedProductA))).and(hasItem(equalTo(scannedProductB))));
    }
}