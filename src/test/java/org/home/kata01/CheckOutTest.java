package org.home.kata01;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.home.kata01.CheckOut.Builder.aCheckOut;
import static org.home.kata01.Products.A;
import static org.home.kata01.Products.B;
import static org.home.kata01.Products.C;
import static org.junit.Assert.assertThat;

@RunWith(DataProviderRunner.class)
public class CheckOutTest {

    private CheckOut checkOut;

    @Before
    public void setUp() throws Exception {
        checkOut = aCheckOut().withProduct(A.toProduct())
                              .withProduct(B.toProduct())
                              .withProduct(C.toProduct())
                              .build();

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
}