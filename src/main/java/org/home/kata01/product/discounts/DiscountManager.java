package org.home.kata01.product.discounts;

import org.home.kata01.product.Price;
import org.home.kata01.product.amount.Amount;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class DiscountManager {
    private final Map<Amount, Discount> rules;

    public DiscountManager() {
        rules = new HashMap<>();
    }

    public void addDiscount(@Nonnull Discount discount) throws IllegalStateException {
        rules.computeIfPresent(discount.amount, (amount, existedRule) -> {
            throw new IllegalStateException("The discount for a given amount of product is already existed.");
        });
        rules.put(discount.amount, discount);
    }

    public void iterateDiscounts(@Nonnull BiFunction<Amount, Price, IteratorState> discountAnalyzer) {
        List<Amount> amounts = getSortedDiscounts();

        int ruleIndex = 0;
        int discountsAmount = amounts.size();

        while (ruleIndex < discountsAmount) {
            Amount ruleAmount = amounts.get(ruleIndex);
            Price price = rules.get(ruleAmount).price;

            IteratorState state = discountAnalyzer.apply(ruleAmount, price);

            if (IteratorState.NEXT_ELEMENT.equals(state)) {
                ruleIndex++;
            }
        }
    }

    @Nonnull
    private List<Amount> getSortedDiscounts() {
        return rules.keySet().stream().sorted(Comparator.<Amount>reverseOrder()).collect(Collectors.toList());
    }

    public enum IteratorState {
        NEXT_ELEMENT, REPEAT_FOR_CURRENT_ELEMENT
    }
}