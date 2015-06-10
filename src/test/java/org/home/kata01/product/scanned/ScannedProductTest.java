package org.home.kata01.product.scanned;

import junitx.extensions.EqualsHashCodeTestCase;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class ScannedProductTest {
    private static final String NAME = "name";

    public static class GeneralFunctionalityTest {
        @Test
        public void specialMessageShouldBeReturnFromToStringMethod() throws Exception {
            ScannedProduct scannedProduct = ScannedProduct.of(NAME);

            String expectedValue = String.format("Product \'%s\' is scanned %d times", NAME, 1);

            assertThat(scannedProduct.toString(), is(equalTo(expectedValue)));
        }
    }

    public static class AmountEqualsAndHashCodeTest extends EqualsHashCodeTestCase {
        public AmountEqualsAndHashCodeTest(String name) {
            super(name);
        }

        @Override
        protected Object createInstance() throws Exception {
            return ScannedProduct.of(NAME);
        }

        @Override
        protected Object createNotEqualInstance() throws Exception {
            ScannedProduct scannedProduct = ScannedProduct.of(NAME);
            scannedProduct.increaseAmount();

            return scannedProduct;
        }
    }
}