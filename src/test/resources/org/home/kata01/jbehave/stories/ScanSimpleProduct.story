Meta: To verify that it is possible to scan one product and our application return correct price for the product.

Narrative: User scans one/single product and calculates its price. The price has to be equaled to price of the scanned product.

Story: Scan simple product.

Given a product "A" with price "20"
And a product "B" with price "30"
And a product "C" with price "10"

When scan product "<productName>"
Then price has to be equaled "<expectedPrice>"
And clean configuration

Examples:
| productName | expectedPrice |
| A           | 20            |
| B           | 30            |
| C           | 10            |