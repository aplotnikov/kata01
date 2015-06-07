package org.home.kata01.product;

import org.home.kata01.product.discounts.Discount;
import org.home.kata01.product.discounts.DiscountManager;
import org.jmlspecs.annotation.Readonly;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.nonNull;
import static org.home.kata01.product.discounts.DiscountManager.IteratorState.NEXT_ELEMENT;
import static org.home.kata01.product.discounts.DiscountManager.IteratorState.REPEAT_FOR_CURRENT_ELEMENT;

public class Product {
    @Readonly
    public final  Name            name;
    @Readonly
    public final  Price           price;
    private final DiscountManager discountManager;

    private Product(@Nonnull String name, @Nonnull Price price, @Nonnull DiscountManager discountManager) {
        this.name = Name.of(name);
        this.price = price;
        this.discountManager = discountManager;
    }

    @Nonnull
    public Price getPriceForAmount(int amount) {
        if (amount == 1) {
            return price;
        }

        return getPriceWithDiscount(amount);
    }

    private Price getPriceWithDiscount(int amount) {
        final Price price = new Price();
        final int[] amountOfProduct = {amount};

        discountManager.iterateDiscounts((productAmountForRule, rulePrice) -> {
            if (productAmountForRule > amountOfProduct[0]) {
                return NEXT_ELEMENT;
            }

            // TODO ugly
            amountOfProduct[0] -= productAmountForRule;
            price.add(rulePrice);

            return REPEAT_FOR_CURRENT_ELEMENT;
        });

        if (amountOfProduct[0] != 0) {
            Price productPrice = this.price.multiply(amountOfProduct[0]);
            price.add(productPrice);
        }

        return price;
    }

    public static class Builder {
        private       String          name;
        private       Price           price;
        private final DiscountManager discountManager;

        private Builder() {
            discountManager = new DiscountManager();
        }

        @Nonnull
        public static Builder aProduct() {
            return new Builder();
        }

        @Nonnull
        public Builder withName(@Nonnull String name) {
            this.name = name;
            return this;
        }

        @Nonnull
        public Builder withPrice(@Nonnull Price price) {
            this.price = price;
            return this;
        }

        @Nonnull
        public Builder withRule(@Nonnull Discount discount) {
            discountManager.addDiscount(discount);
            return this;
        }

        @Nonnull
        public Product create() {
            checkState(nonNull(name), "'name' parameter has to be initialized.");
            checkState(nonNull(price), "'price' parameter has to be initialized.");

            return new Product(name, price, discountManager);
        }
    }
}