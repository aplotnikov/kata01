package org.home.kata01.product.discounts;

import org.home.kata01.product.Price;
import org.home.kata01.product.amount.Amount;
import org.home.kata01.product.amount.MutableAmount;
import org.home.kata01.product.discounts.DiscountManager.IteratorState;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.home.kata01.product.discounts.Discount.Builder.aDiscount;
import static org.junit.Assert.assertThat;

public class DiscountManagerTest {
    private enum Discounts {
        FIRST(1, 10),
        SECOND(2, 15);

        private final int    amount;
        private final double price;

        Discounts(int amount, double price) {
            this.amount = amount;
            this.price = price;
        }

        @Nonnull
        public Discount toDiscount() {
            return aDiscount().forProductAmount(amount)
                              .withPrice(price)
                              .create();
        }
    }

    private DiscountManager manager;

    @Before
    public void setUp() throws Exception {
        manager = new DiscountManager();
    }

    @Test(expected = IllegalStateException.class)
    public void exceptionShouldBeThrownWhenTryingToPutInDuplicateDiscount() throws Exception {
        manager.addDiscount(Discounts.FIRST.toDiscount());
        manager.addDiscount(Discounts.FIRST.toDiscount());
    }

    @Test
    public void iterationProcessShouldBePerformed() throws Exception {
        Discount firstDiscount = Discounts.FIRST.toDiscount();
        Discount secondDiscount = Discounts.SECOND.toDiscount();

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