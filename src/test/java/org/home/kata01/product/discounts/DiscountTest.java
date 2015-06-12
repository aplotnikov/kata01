package org.home.kata01.product.discounts;

import junitx.extensions.EqualsHashCodeTestCase;

import org.home.kata01.product.Price;
import org.home.kata01.product.amount.Amount;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.home.kata01.product.amount.Amount.Builder.anAmount;
import static org.home.kata01.product.discounts.Discount.Builder.aDiscount;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class DiscountTest {
    private enum TestNumber {
        INCORRECT(-1),
        FIVE(5),
        TEN(10);

        private final int value;

        TestNumber(int value) {
            this.value = value;
        }

        public int toInt() {
            return value;
        }
    }

    public static class GeneralFunctionalityTest {
        @Test
        public void specialMessageShouldBeReturnFromToStringMethod() throws Exception {
            Discount discount = aDiscount().forProductAmount(TestNumber.TEN.toInt())
                                           .withPrice(TestNumber.TEN.toInt())
                                           .create();

            String expectedValue = String.format("Discount for %d products with price %s",
                                                 TestNumber.TEN.toInt(),
                                                 Price.of(TestNumber.TEN.toInt()));

            assertThat(discount.toString(), is(equalTo(expectedValue)));
        }
    }

    public static class AmountEqualsAndHashCodeTest extends EqualsHashCodeTestCase {
        public AmountEqualsAndHashCodeTest(String name) {
            super(name);
        }

        @Override
        protected Object createInstance() throws Exception {
            return aDiscount().forProductAmount(TestNumber.TEN.toInt())
                              .withPrice(TestNumber.TEN.toInt())
                              .create();
        }

        @Override
        protected Object createNotEqualInstance() throws Exception {
            return aDiscount().forProductAmount(TestNumber.FIVE.toInt())
                              .withPrice(TestNumber.FIVE.toInt())
                              .create();
        }
    }

    public static class BuilderTest {
        @Test(expected = IllegalStateException.class)
        public void exceptionShouldBeThrownWhenAmountParameterIsLessThanZero() throws Exception {
            aDiscount().forProductAmount(TestNumber.INCORRECT.toInt()).create();
        }

        @Test(expected = IllegalStateException.class)
        public void exceptionShouldBeThrownWhenPriceParameterIsEmpty() throws Exception {
            aDiscount().withPrice(TestNumber.TEN.toInt()).create();
        }

        @Test
        public void instanceShouldBeCreated() throws Exception {
            Amount expectedAmount = anAmount().withValue(TestNumber.FIVE.toInt())
                                              .create();
            Price expectedPrice = Price.of(TestNumber.TEN.toInt());

            Discount product = aDiscount().forProductAmount(TestNumber.FIVE.toInt())
                                          .withPrice(TestNumber.TEN.toInt())
                                          .create();

            assertThat(product, is(notNullValue()));
            assertThat(product.amount, is(equalTo(expectedAmount)));
            assertThat(product.price, is(equalTo(expectedPrice)));
        }
    }
}