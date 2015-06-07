package org.home.kata01;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.home.kata01.product.Price;
import org.home.kata01.product.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.home.kata01.CheckOut.Builder.aCheckOut;
import static org.home.kata01.Products.A;
import static org.home.kata01.Products.B;
import static org.home.kata01.Products.C;
import static org.home.kata01.product.Product.Builder.aProduct;
import static org.home.kata01.product.discounts.Discount.Builder.aDiscount;
import static org.junit.Assert.assertThat;

@RunWith(DataProviderRunner.class)
public class CheckOutTest {
    private CheckOut checkOut;

    @Before
    public void setUp() throws Exception {
        Product productA = aProduct().withName(A.getName())
                                     .withPrice(A.getPrice())
                                     .withRule(
                                             aDiscount().withProductAmount(3)
                                                        .withPrice(Price.of(20))
                                                        .create()
                                              )
                                     .withRule(
                                             aDiscount().withProductAmount(4)
                                                        .withPrice(Price.of(25))
                                                        .create()
                                              )
                                     .create();

        Product productB = aProduct().withName(B.getName())
                                     .withPrice(B.getPrice())
                                     .withRule(
                                             aDiscount().withProductAmount(2)
                                                        .withPrice(Price.of(30))
                                                        .create()
                                              ).create();

        checkOut = aCheckOut().withProduct(productA)
                              .withProduct(productB)
                              .withProduct(C.toProduct())
                              .create();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldBeImpossibleToAddTwoProductsWithTheSameName() throws Exception {
        aCheckOut().withProduct(A.toProduct())
                   .withProduct(A.toProduct());
    }

    @DataProvider
    public static Object[][] simpleProducts() {
        return new Object[][]{
                {A},
                {B},
                {C}
        };
    }

    @Test
    @UseDataProvider("simpleProducts")
    public void shouldBeAbleToCalculatePriceForOneProduct(Products product) throws Exception {
        checkOut.scan(product.getName());

        assertThat(checkOut.getPrice(), is(product.getPrice()));
    }

    @DataProvider
    public static Object[][] coupleProducts() {
        return new Object[][]{
                {"AB", Price.of(30)},
                {"BA", Price.of(30)},
                {"ABC", Price.of(60)}
        };
    }

    @Test
    @UseDataProvider("coupleProducts")
    public void shouldBeAbleToCalculatePriceForCoupleProductWithoutRepeatingName(String products, Price expectedPrice)
            throws Exception {
        priceShouldBeEqualedToExpectedPriceForGivenProducts(products, expectedPrice);
    }

    @DataProvider
    public static Object[][] coupleProductsWithRepeatingName() {
        return new Object[][]{
                {"AAA", Price.of(20)},
                {"AAAA", Price.of(25)},
                {"AAAAA", Price.of(35)},
                {"AAAAAA", Price.of(45)},
                {"AAAAAAA", Price.of(45)},
                {"BB", Price.of(30)},
                {"BBB", Price.of(50)},
                {"AABA", Price.of(40)},
                {"AABAC", Price.of(70)}
        };
    }

    @Test
    @UseDataProvider("coupleProductsWithRepeatingName")
    public void shouldBeAbleToCalculatePriceForCoupleProductWithRepeatingName(String products, Price expectedPrice)
            throws Exception {
        priceShouldBeEqualedToExpectedPriceForGivenProducts(products, expectedPrice);
    }

    private void priceShouldBeEqualedToExpectedPriceForGivenProducts(String products, Price expectedPrice) {
        for (String product : products.split("")) {
            checkOut.scan(product);
        }

        assertThat(checkOut.getPrice(), is(expectedPrice));
    }
}