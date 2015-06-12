package org.home.kata01;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.home.kata01.product.Price;
import org.home.kata01.product.Product;
import org.home.kata01.utils.TestDiscount;
import org.home.kata01.utils.TestName;
import org.home.kata01.utils.TestPrice;
import org.home.kata01.utils.TestProduct;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.home.kata01.CheckOut.Builder.aCheckOut;
import static org.home.kata01.product.Product.Builder.aProduct;
import static org.junit.Assert.assertThat;

@RunWith(DataProviderRunner.class)
public class CheckOutTest {
    private CheckOut checkOut;

    @Before
    public void setUp() throws Exception {
        Product productA = aProduct().withName(TestName.A.name())
                                     .withPrice(TestPrice.TEN.getValue())
                                     .withDiscount(TestDiscount.SECOND.toDiscount())
                                     .withDiscount(TestDiscount.THIRD.toDiscount())
                                     .create();

        Product productB = aProduct().withName(TestName.B.name())
                                     .withPrice(TestPrice.FIVE.getValue())
                                     .withDiscount(TestDiscount.SECOND.toDiscount())
                                     .create();

        checkOut = aCheckOut().withProduct(productA)
                              .withProduct(productB)
                              .withProduct(TestProduct.C.toProduct())
                              .create();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldBeImpossibleToAddTwoProductsWithTheSameName() throws Exception {
        aCheckOut().withProduct(TestProduct.A.toProduct())
                   .withProduct(TestProduct.A.toProduct());
    }

    @DataProvider
    public static Object[][] simpleProducts() {
        return new Object[][]{
                {TestProduct.A.toProduct()},
                {TestProduct.B.toProduct()},
                {TestProduct.C.toProduct()}
        };
    }

    @Test
    @UseDataProvider("simpleProducts")
    public void shouldBeAbleToCalculatePriceForOneProduct(Product product) throws Exception {
        checkOut.scan(product.name.toString());

        assertThat(checkOut.getPrice(), is(equalTo(product.price)));
    }

    @DataProvider
    public static Object[][] coupleProducts() {
        return new Object[][]{
                {"AB", Price.of(15)},
                {"BA", Price.of(15)},
                {"ABC", Price.of(35)}
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
                {"AAA", Price.of(7)},
                {"AAAA", Price.of(17)},
                {"AAAAA", Price.of(12)},
                {"AAAAAA", Price.of(14)},
                {"AAAAAAA", Price.of(24)},
                {"BB", Price.of(5)},
                {"BBB", Price.of(10)},
                {"AABA", Price.of(12)},
                {"AABAC", Price.of(32)}
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