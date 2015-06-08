package org.home.kata01.product.amount;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AmountTest {
    private class DummyAmount extends AbstractAmount {
        protected DummyAmount(int amount) {
            super(amount);
        }
    }

    private static final int ZERO = 0;
    private static final int ONE  = 1;
    private static final int FIVE = 5;

    @Test
    public void shouldBeZeroValue() throws Exception {
        DummyAmount amount = new DummyAmount(ZERO);

        assertThat(amount.isZero(), is(true));
    }

    @Test
    public void shouldBeNotZeroValue() throws Exception {
        DummyAmount amount = new DummyAmount(ONE);

        assertThat(amount.isZero(), is(false));
    }

    @Test
    public void shouldBeOneValue() throws Exception {
        DummyAmount amount = new DummyAmount(ONE);

        assertThat(amount.isOne(), is(true));
    }

    @Test
    public void shouldBeNotOneValue() throws Exception {
        DummyAmount amount = new DummyAmount(ZERO);

        assertThat(amount.isOne(), is(false));
    }

    @Test
    public void givenAmountShouldBeLessThanCurrentAmount() throws Exception {
        DummyAmount lessAmount = new DummyAmount(ZERO);
        DummyAmount biggerAmount = new DummyAmount(ONE);

        assertThat(lessAmount.isBigger(biggerAmount), is(false));
    }

    @Test
    public void givenAmountShouldBeBiggerThanCurrentAmount() throws Exception {
        DummyAmount lessAmount = new DummyAmount(ZERO);
        DummyAmount biggerAmount = new DummyAmount(ONE);

        assertThat(biggerAmount.isBigger(lessAmount), is(true));
    }

    @Test
    public void valueShouldBeSubtractedFromAmount() throws Exception {
        DummyAmount amount = new DummyAmount(FIVE);
        DummyAmount subtrahend = new DummyAmount(ONE);

        assertThat(amount.value(), is(FIVE));

        amount.subtract(subtrahend);

        assertThat(amount.value(), is(4));
    }

    @Test
    public void valueShouldBeIncreased() throws Exception {
        DummyAmount amount = new DummyAmount(ZERO);

        assertThat(amount.value(), is(ZERO));

        amount.increase();

        assertThat(amount.value(), is(ONE));
    }
}