package org.home.kata01.jbehave;

import org.home.kata01.CheckOut;
import org.home.kata01.product.Price;
import org.home.kata01.product.Product.Builder;
import org.home.kata01.product.discounts.Discount;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.home.kata01.CheckOut.Builder.aCheckOut;
import static org.home.kata01.product.discounts.Discount.Builder.aDiscount;
import static org.junit.Assert.assertThat;

public class CheckOutSteps {
    private CheckOut.Builder   checkOutBuilder;
    private Optional<Builder>  productBuilder;
    private Optional<CheckOut> checkOut;

    @BeforeScenario
    public void prepareScenario() throws Exception {
        cleanCheckOutConfiguration();
    }

    @BeforeStory
    public void prepareStory() throws Exception {
        cleanCheckOutConfiguration();
    }

    @Given("products: $products")
    public void products(@Nonnull ExamplesTable products) {
        products.getRows().stream().forEach(this::processTableRow);
    }

    private void processTableRow(@Nonnull Map<String, String> columns) {
        String name = columns.get("name");
        double price = Double.valueOf(columns.get("price"));

        aProduct(name, price);
    }

    @Given("a product \"$name\" with price \"$price\"")
    @Alias("a product \"<name>\" with price \"<price>\"")
    public void aProduct(@Named("name") @Nonnull String name, @Named("price") double price) {
        productBuilder.ifPresent(product -> checkOutBuilder.withProduct(product.create()));
        productBuilder = Optional.of(Builder.aProduct().withName(name).withPrice(price));
    }

    @Given("a discount for $amount products price is $price")
    public void withDiscount(int amount, double price) throws IllegalStateException {
        Discount discount = aDiscount().forProductAmount(amount)
                                       .withPrice(price)
                                       .create();
        productBuilder.orElseThrow(IllegalStateException::new).withDiscount(discount);
    }

    @When("scan product \"<productName>\"")
    public void scanProducts(@Named("productName") @Nonnull String productName) {
        theCheckOut().scan(productName);
    }

    @When("scan products: $products")
    public void scanProducts(@Nonnull ExamplesTable products) {
        products.getRows().stream().map(columns -> columns.get("name")).forEach(this::scanProducts);
    }

    @Then("price has to be equaled \"$expectedPrice\"")
    public void verifyPrice(@Named("$expectedPrice") double expectedPrice) {
        assertThat(theCheckOut().getPrice(), is(equalTo(Price.of(expectedPrice))));
    }

    @Then("clean configuration")
    public void cleanCheckOutConfiguration() {
        checkOutBuilder = aCheckOut();
        productBuilder = Optional.empty();
        checkOut = Optional.empty();
    }

    @Nonnull
    private CheckOut theCheckOut() {
        return checkOut.orElseGet(() -> {
            productBuilder.ifPresent(product -> checkOutBuilder.withProduct(product.create()));
            CheckOut newCheckOut = checkOutBuilder.create();
            checkOut = Optional.of(newCheckOut);
            return newCheckOut;
        });
    }
}