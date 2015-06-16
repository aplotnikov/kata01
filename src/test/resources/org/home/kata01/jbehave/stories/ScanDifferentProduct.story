Meta: To verify possibility to scan a few different product and get correct price for them

Narrative:
User scans a few different products without any discounts and tries to get price for them.

Scenario: scan a few diffrent products

Given products:
| name | price |
| A    | 10    |
| B    | 20    |
| C    | 30    |

When scan products:
| name |
| A    |
| B    |
| C    |
Then price has to be equaled "60"