package org.home.kata01.product.amount;

import junitx.extensions.ComparabilityTestCase;
import junitx.extensions.EqualsHashCodeTestCase;

import org.home.kata01.utils.TestAmount;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class MutableAmountTest {
    public static class GeneralFunctionalityTest {
        @Test
        public void shouldBeZeroValue() throws Exception {
            Amount amount = TestAmount.ZERO.toAmount();
            assertThat(amount.isZero(), is(true));
        }

        @Test
        public void shouldBeNotZeroValue() throws Exception {
            Amount amount = TestAmount.ONE.toAmount();
            assertThat(amount.isZero(), is(false));
        }

        @Test
        public void shouldBeOneValue() throws Exception {
            Amount amount = TestAmount.ONE.toAmount();
            assertThat(amount.isOne(), is(true));
        }

        @Test
        public void shouldBeNotOneValue() throws Exception {
            Amount amount = TestAmount.ZERO.toAmount();
            assertThat(amount.isOne(), is(false));
        }

        @Test
        public void givenAmountShouldBeLessThanCurrentAmount() throws Exception {
            Amount lessAmount = TestAmount.ZERO.toAmount();
            Amount biggerAmount = TestAmount.ONE.toAmount();

            assertThat(lessAmount.isBigger(biggerAmount), is(false));
        }

        @Test
        public void givenAmountShouldBeBiggerThanCurrentAmount() throws Exception {
            Amount lessAmount = TestAmount.ZERO.toAmount();
            Amount biggerAmount = TestAmount.ONE.toAmount();

            assertThat(biggerAmount.isBigger(lessAmount), is(true));
        }

        @Test
        public void valueShouldBeSubtractedFromAmount() throws Exception {
            Amount amount = TestAmount.FIVE.toAmount();
            Amount subtrahend = TestAmount.ONE.toAmount();

            assertThat(amount.value(), is(TestAmount.FIVE.toInt()));

            amount.subtract(subtrahend);

            assertThat(amount.value(), is(4));
        }

        @Test
        public void valueShouldBeIncreased() throws Exception {
            Amount amount = TestAmount.ZERO.toAmount();

            assertThat(amount.value(), is(TestAmount.ZERO.toInt()));

            amount.increase();

            assertThat(amount.value(), is(TestAmount.ONE.toInt()));
        }

        @Test
        public void specialMessageShouldBeReturnFromTOStringMethod() throws Exception {
            Amount amount = TestAmount.ONE.toAmount();
            String expectedValue = String.format("Mutable %d amount", TestAmount.ONE.toInt());

            assertThat(amount.toString(), is(equalTo(expectedValue)));
        }
    }

    public static class AmountCompatibilityTest extends ComparabilityTestCase {
        public AmountCompatibilityTest(String name) {
            super(name);
        }

        @Override
        protected Comparable createLessInstance() throws Exception {
            return TestAmount.ZERO.toAmount();
        }

        @Override
        protected Comparable createEqualInstance() throws Exception {
            return TestAmount.ONE.toAmount();
        }

        @Override
        protected Comparable createGreaterInstance() throws Exception {
            return TestAmount.FIVE.toAmount();
        }
    }

    public static class AmountEqualsAndHashCodeTest extends EqualsHashCodeTestCase {
        public AmountEqualsAndHashCodeTest(String name) {
            super(name);
        }

        @Override
        protected Object createInstance() throws Exception {
            return TestAmount.ZERO.toAmount();
        }

        @Override
        protected Object createNotEqualInstance() throws Exception {
            return TestAmount.ONE.toAmount();
        }
    }
}