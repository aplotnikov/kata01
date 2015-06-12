package org.home.kata01.product;

import checkers.igj.quals.Immutable;

import org.home.kata01.product.amount.Amount;
import org.home.kata01.product.discounts.Discount;
import org.home.kata01.product.discounts.DiscountManager;

import javax.annotation.Nonnull;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.nonNull;
import static org.home.kata01.product.amount.Amount.Builder.anAmount;
import static org.home.kata01.product.discounts.DiscountManager.IteratorState.NEXT_ELEMENT;
import static org.home.kata01.product.discounts.DiscountManager.IteratorState.REPEAT_FOR_CURRENT_ELEMENT;

@Immutable
public class Product {
    public final  Name            name;
    public final  Price           price;
    private final DiscountManager discountManager;

    private Product(@Nonnull Name name, @Nonnull Price price, @Nonnull DiscountManager discountManager) {
        this.name = name;
        this.price = price;
        this.discountManager = discountManager;
    }

    @Nonnull
    public Price getPriceForAmount(@Nonnull Amount amount) {
        if (amount.isOne()) {
            return price;
        }

        return getPriceWithDiscount(amount.value());
    }

    @Nonnull
    private Price getPriceWithDiscount(int amount) {
        final Price resultPrice = Price.zero();
        final Amount amountOfProduct = anAmount().withValue(amount).isMutable().create();

        discountManager.iterateDiscounts((productAmountForDiscount, discountPrice) -> {
            if (productAmountForDiscount.isBigger(amountOfProduct)) {
                return NEXT_ELEMENT;
            }

            amountOfProduct.subtract(productAmountForDiscount);
            resultPrice.add(discountPrice);

            return REPEAT_FOR_CURRENT_ELEMENT;
        });

        if (!amountOfProduct.isZero()) {
            Price productPrice = price.multiply(amountOfProduct);
            resultPrice.add(productPrice);
        }

        return resultPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Product)) {
            return false;
        }

        Product product = (Product)o;
        return Objects.equals(name, product.name) &&
               Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return String.format("\'%s\' product with price %s", name.toString(), price.toString());
    }

    public static class Builder {
        private       String          name;
        private       double          price;
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
        public Builder withPrice(double price) {
            this.price = price;
            return this;
        }

        @Nonnull
        public Builder withDiscount(@Nonnull Discount discount) {
            discountManager.addDiscount(discount);
            return this;
        }

        @Nonnull
        public Product create() {
            checkState(nonNull(name), "'name' parameter has to be initialized.");
            checkState(price > 0, "'price' parameter has to be not less than zero.");

            return new Product(Name.of(name), Price.of(price), discountManager);
        }
    }
}