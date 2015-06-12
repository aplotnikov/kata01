package org.home.kata01.product.scanned;

import junitx.extensions.EqualsHashCodeTestCase;

import org.home.kata01.product.utils.TestName;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class ScannedProductTest {
    public static class GeneralFunctionalityTest {
        @Test
        public void specialMessageShouldBeReturnFromToStringMethod() throws Exception {
            ScannedProduct scannedProduct = ScannedProduct.of(TestName.FIRST.name());

            String expectedValue = String.format("Product \'%s\' is scanned %d times", TestName.FIRST.name(), 1);

            assertThat(scannedProduct.toString(), is(equalTo(expectedValue)));
        }
    }

    public static class AmountEqualsAndHashCodeTest extends EqualsHashCodeTestCase {
        public AmountEqualsAndHashCodeTest(String name) {
            super(name);
        }

        @Override
        protected Object createInstance() throws Exception {
            return ScannedProduct.of(TestName.FIRST.name());
        }

        @Override
        protected Object createNotEqualInstance() throws Exception {
            ScannedProduct scannedProduct = ScannedProduct.of(TestName.FIRST.name());
            scannedProduct.increaseAmount();

            return scannedProduct;
        }
    }
}