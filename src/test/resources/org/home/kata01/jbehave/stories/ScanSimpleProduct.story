Story: Scan simple product

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