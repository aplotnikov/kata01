package org.home.kata01.product.discounts;

import junitx.extensions.EqualsHashCodeTestCase;

import org.home.kata01.product.Price;
import org.home.kata01.product.amount.Amount;
import org.home.kata01.utils.TestAmount;
import org.home.kata01.utils.TestDiscount;
import org.home.kata01.utils.TestPrice;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.home.kata01.product.discounts.Discount.Builder.aDiscount;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class DiscountTest {
    public static class GeneralFunctionalityTest {
        @Test
        public void specialMessageShouldBeReturnFromToStringMethod() throws Exception {
            String expectedValue = String.format("Discount for %d products with price %s",
                                                 TestAmount.FIVE.toInt(),
                                                 TestPrice.TEN.toPrice());
            assertThat(TestDiscount.FIRST.toDiscount().toString(), is(equalTo(expectedValue)));
        }
    }

    public static class AmountEqualsAndHashCodeTest extends EqualsHashCodeTestCase {
        public AmountEqualsAndHashCodeTest(String name) {
            super(name);
        }

        @Override
        protected Object createInstance() throws Exception {
            return TestDiscount.FIRST.toDiscount();
        }

        @Override
        protected Object createNotEqualInstance() throws Exception {
            return TestDiscount.SECOND.toDiscount();
        }
    }

    public static class BuilderTest {
        private static final int INCORRECT_VALUE = -1;

        @Test(expected = IllegalStateException.class)
        public void exceptionShouldBeThrownWhenAmountParameterIsLessThanZero() throws Exception {
            aDiscount().forProductAmount(INCORRECT_VALUE).create();
        }

        @Test(expected = IllegalStateException.class)
        public void exceptionShouldBeThrownWhenPriceParameterIsEmpty() throws Exception {
            aDiscount().withPrice(TestPrice.TEN.getValue()).create();
        }

        @Test
        public void instanceShouldBeCreated() throws Exception {
            Amount expectedAmount = TestAmount.FIVE.toAmount();
            Price expectedPrice = TestPrice.TEN.toPrice();

            Discount product = aDiscount().forProductAmount(TestAmount.FIVE.toInt())
                                          .withPrice(TestPrice.TEN.getValue())
                                          .create();

            assertThat(product, is(notNullValue()));
            assertThat(product.amount, is(equalTo(expectedAmount)));
            assertThat(product.price, is(equalTo(expectedPrice)));
        }
    }
}