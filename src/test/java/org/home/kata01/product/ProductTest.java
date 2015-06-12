package org.home.kata01.product;

import junitx.extensions.EqualsHashCodeTestCase;

import org.home.kata01.product.discounts.Discount;
import org.home.kata01.utils.TestAmount;
import org.home.kata01.utils.TestDiscount;
import org.home.kata01.utils.TestName;
import org.home.kata01.utils.TestPrice;
import org.home.kata01.utils.TestProduct;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.home.kata01.product.Product.Builder.aProduct;
import static org.home.kata01.product.discounts.Discount.Builder.aDiscount;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class ProductTest {
    public static class GeneralFunctionalityTest {
        private Product product;

        @Before
        public void setUp() throws Exception {
            product = aProduct().withName(TestName.A.name())
                                .withPrice(TestPrice.TEN.getValue())
                                .withDiscount(TestDiscount.FIRST.toDiscount())
                                .create();
        }

        @Test
        public void priceShouldBeCalculatedWhenAmountOfProductIsOne() throws Exception {
            Price price = product.getPriceForAmount(TestAmount.ONE.toAmount());

            assertThat(price, is(equalTo(TestPrice.TEN.toPrice())));
        }

        @Test
        public void priceShouldBeCalculatedWhenDiscountIsFound() throws Exception {
            Price price = product.getPriceForAmount(TestAmount.FIVE.toAmount());

            assertThat(price, is(equalTo(TestDiscount.FIRST.toDiscount().price)));
        }

        @Test
        public void priceShouldBeCalculatedWhenDiscountIsNotFound() throws Exception {
            Price price = product.getPriceForAmount(TestAmount.TWO.toAmount());

            assertThat(price, is(equalTo(TestPrice.TWENTY.toPrice())));
        }

        @Test
        public void specialMessageShouldBeReturnFromToStringMethod() throws Exception {
            String expectedValue = String.format("\'%s\' product with price %s",
                                                 TestName.A.toName().toString(),
                                                 TestPrice.TEN.toPrice().toString());

            assertThat(TestProduct.A.toProduct().toString(), is(equalTo(expectedValue)));
        }
    }

    public static class ProductEqualsAndHashCodeTest extends EqualsHashCodeTestCase {
        public ProductEqualsAndHashCodeTest(String name) {
            super(name);
        }

        @Override
        protected Object createInstance() throws Exception {
            return TestProduct.A.toProduct();
        }

        @Override
        protected Object createNotEqualInstance() throws Exception {
            return TestProduct.B.toProduct();
        }
    }

    public static class BuilderTest {
        @Test(expected = IllegalStateException.class)
        public void exceptionShouldBeThrownWhenNameParameterIsEmpty() throws Exception {
            aProduct().create();
        }

        @Test(expected = IllegalStateException.class)
        public void exceptionShouldBeThrownWhenPriceParameterIsEmpty() throws Exception {
            aProduct().withName(TestName.A.name()).create();
        }

        @Test(expected = IllegalStateException.class)
        public void exceptionShouldBeThrownWhenRuleWithTheSameAmountOfProductIsAlreadyExisted() throws Exception {
            Discount discount = aDiscount().forProductAmount(TestAmount.ONE.toInt())
                                           .withPrice(TestPrice.TEN.getValue())
                                           .create();

            aProduct().withDiscount(discount).withDiscount(discount).create();
        }

        @Test
        public void instanceShouldBeCreated() throws Exception {
            Product product = TestProduct.A.toProduct();

            assertThat(product, is(notNullValue()));
            assertThat(product.name, is(equalTo(TestName.A.toName())));
            assertThat(product.price, is(equalTo(TestPrice.TEN.toPrice())));
        }
    }
}