package org.home.kata01.product.discounts;

import org.home.kata01.product.Price;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class DiscountManager {
    private final Map<Integer, Discount> rules;

    public DiscountManager() {
        rules = new HashMap<>();
    }

    public void addDiscount(@Nonnull Discount discount) throws IllegalStateException {
        rules.computeIfPresent(discount.amount, (amount, existedRule) -> {
            throw new IllegalStateException("The discount for a given amount of product is already existed.");
        });
        rules.put(discount.amount, discount);
    }

    public void iterateDiscounts(BiFunction<Integer, Price, IteratorState> ruleAnalyzer) {
        List<Integer> amounts = getSortedDiscounts();

        int ruleIndex = 0;
        int discountsAmount = amounts.size();

        while (ruleIndex < discountsAmount) {
            int ruleAmount = amounts.get(ruleIndex);
            Price price = rules.get(ruleAmount).price;
            IteratorState state = ruleAnalyzer.apply(ruleAmount, price);

            if (IteratorState.NEXT_ELEMENT.equals(state)) {
                ruleIndex++;
            }
        }
    }

    private @Nonnull List<Integer> getSortedDiscounts() {
        List<Integer> amounts = new ArrayList<>(rules.keySet());
        Collections.sort(amounts, Comparator.<Integer>reverseOrder());

        return amounts;
    }

    public enum IteratorState {
        NEXT_ELEMENT, REPEAT_FOR_CURRENT_ELEMENT
    }
}