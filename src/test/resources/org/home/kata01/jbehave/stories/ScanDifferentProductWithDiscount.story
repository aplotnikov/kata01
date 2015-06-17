Meta: To verify scaning a few products with discounts

Narrative:
User scans a few product with disocunts and these discounts have to impack on the final price.
So this story verifies case when user tries to buy products with disocunt.

Scenario: Scan two the same products and have discount for this amount of products

Given a product "A" with price "10"
And a discount for 2 products price is 5
When scan products:
| name |
| A    |
| A    |
Then price has to be equaled "5"

Scenario: Scan three the same products and have two different discounts for this product. The correct one has to be chosen.
The second disocunt (for three products) has to be chosen.

Given a product "A" with price "10"
And a discount for 2 products price is 5
And a discount for 3 products price is 7
When scan products:
| name |
| A    |
| A    |
| A    |
Then price has to be equaled "7"

Scenario: Scan four the same products and have two different discounts for this product. The correct one has to be chosen.
The second disocunt has to be chosen for three products and the fourth one has to have normal price.

Given a product "A" with price "10"
And a discount for 2 products price is 5
And a discount for 3 products price is 7
When scan products:
| name |
| A    |
| A    |
| A    |
| A    |
Then price has to be equaled "17"

Scenario: Scan five the same products and have two different discounts for this product. The correct one has to be chosen.
For the first two products the first disocunt has to be chosen and for the next one the second disocunt has to be chosen.

Given a product "A" with price "10"
And a discount for 2 products price is 5
And a discount for 3 products price is 7
When scan products:
| name |
| A    |
| A    |
| A    |
| A    |
| A    |
Then price has to be equaled "12"

Scenario: Scan six the same products and have two different discounts for this product. The correct one has to be chosen.
The second disocunt has to be chosen two times.

Given a product "A" with price "10"
And a discount for 2 products price is 5
And a discount for 3 products price is 7
When scan products:
| name |
| A    |
| A    |
| A    |
| A    |
| A    |
| A    |
Then price has to be equaled "14"

Scenario: Scan seven the same products and have two different discounts for this product. The correct one has to be chosen.
The second disocunt has to be chosen twice and the last product has to have normal price.

Given a product "A" with price "10"
And a discount for 2 products price is 5
And a discount for 3 products price is 7
When scan products:
| name |
| A    |
| A    |
| A    |
| A    |
| A    |
| A    |
| A    |
Then price has to be equaled "24"

Scenario: Scan three the same products and one another one. For all products the discounts are created.
One disocunt for first prdduct has to be chosen.

Given a product "A" with price "10"
And a discount for 2 products price is 5
And a discount for 3 products price is 7
And a product "B" with price "20"
And a discount for 2 products price is 10
When scan products:
| name |
| A    |
| B    |
| A    |
| A    |
Then price has to be equaled "27"

Scenario: Scan three the same products and one another one. For all products the discounts are created.
Discounts have to be chosen for both products.

Given a product "A" with price "10"
And a discount for 2 products price is 5
And a discount for 3 products price is 7
And a product "B" with price "20"
And a discount for 2 products price is 10
When scan products:
| name |
| A    |
| B    |
| A    |
| B    |
| A    |
Then price has to be equaled "17"