package org.home.kata01.product.discounts;

import org.home.kata01.product.Price;
import org.home.kata01.product.amount.Amount;
import org.home.kata01.product.amount.MutableAmount;
import org.home.kata01.product.discounts.DiscountManager.IteratorState;
import org.home.kata01.utils.TestDiscount;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertThat;

public class DiscountManagerTest {
    private DiscountManager manager;

    @Before
    public void setUp() throws Exception {
        manager = new DiscountManager();
    }

    @Test(expected = IllegalStateException.class)
    public void exceptionShouldBeThrownWhenTryingToPutInDuplicateDiscount() throws Exception {
        manager.addDiscount(TestDiscount.FIRST.toDiscount());
        manager.addDiscount(TestDiscount.FIRST.toDiscount());
    }

    @Test
    public void iterationProcessShouldBePerformed() throws Exception {
        Discount firstDiscount = TestDiscount.FIRST.toDiscount();
        Discount secondDiscount = TestDiscount.SECOND.toDiscount();

        manager.addDiscount(firstDiscount);
        manager.addDiscount(secondDiscount);

        Amount amountOfIteration = MutableAmount.of(0);
        Map<Amount, Price> discounts = new HashMap<>();

        manager.iterateDiscounts((amount, price) -> {
            amountOfIteration.increase();
            discounts.put(amount, price);

            return IteratorState.NEXT_ELEMENT;
        });

        assertThat(amountOfIteration.value(), is(equalTo(2)));
        assertThat(discounts, both(hasEntry(firstDiscount.amount, firstDiscount.price))
                .and(hasEntry(secondDiscount.amount, secondDiscount.price)));
    }
}