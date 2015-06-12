package org.home.kata01.product.amount;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ImmutableAmountTest {
    private static final int ONE = 1;

    private Amount amount;

    @Before
    public void setUp() throws Exception {
        amount = ImmutableAmount.of(ONE);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void exceptionShouldBeThrownWhenIncreaseMethodIsExecuted() throws Exception {
        amount.increase();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void exceptionShouldBeThrownWhenSubtractMethodIsExecuted() throws Exception {
        amount.subtract(amount);
    }

    @Test
    public void specialMessageShouldBeReturnFromTOStringMethod() throws Exception {
        String expectedValue = String.format("Immutable %d amount", ONE);
        assertThat(amount.toString(), is(equalTo(expectedValue)));
    }
}