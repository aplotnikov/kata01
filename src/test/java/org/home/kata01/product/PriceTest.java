package org.home.kata01.product;

import junitx.extensions.EqualsHashCodeTestCase;

import org.home.kata01.product.amount.Amount;
import org.home.kata01.utils.TestAmount;
import org.home.kata01.utils.TestPrice;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class PriceTest {
    public static class GeneralFunctionalityTest {
        @Test
        public void valueShouldBeAddedToPrice() throws Exception {
            Price price = TestPrice.TEN.toPrice();
            Price otherPrice = TestPrice.TEN.toPrice();

            price.add(otherPrice);

            assertThat(price, is(equalTo(TestPrice.TWENTY.toPrice())));
        }

        @Test
        public void valueShouldBeMultipliedByValue() throws Exception {
            Price price = TestPrice.TEN.toPrice();
            Amount multiplier = TestAmount.FIVE.toAmount();

            price.multiply(multiplier);

            assertThat(price, is(equalTo(TestPrice.FIFTY.toPrice())));
        }

        @Test
        public void instanceShouldBeCreatedFromDoubleValue() throws Exception {
            Price price = TestPrice.TEN.toPrice();
            Price actualPrice = TestPrice.TEN.toPrice();

            assertThat(actualPrice, is(notNullValue()));
            assertThat(actualPrice, is(equalTo(price)));
        }

        @Test
        public void specialMessageShouldBeReturnFromToStringMethod() throws Exception {
            String actualValue = TestPrice.TEN.toPrice().toString();
            String expectedValue = BigDecimal.valueOf(TestPrice.TEN.getValue()).toString();

            assertThat(actualValue, is(equalTo(expectedValue)));
        }

        @Test
        public void zeroPriceShouldBeCreated() throws Exception {
            assertThat(Price.zero(), is(equalTo(TestPrice.ZERO.toPrice())));
        }
    }

    public static class PriceEqualsAndHashCodeTest extends EqualsHashCodeTestCase {
        public PriceEqualsAndHashCodeTest(String name) {
            super(name);
        }

        @Override
        protected Object createInstance() throws Exception {
            return TestPrice.TEN.toPrice();
        }

        @Override
        protected Object createNotEqualInstance() throws Exception {
            return TestPrice.FIVE.toPrice();
        }
    }
}