Feature: Taco Shop Discounts

  For the taco shopper
  Who thinks that loyalty has its rewards
  The Taco Shop Discount Program
  Is a loyalty program that rewards frequent patrons
  Unlike our competitor's program
  Our program gives you rewards today for bigger orders

  Scenario Outline: purchase over 15 and less than or equal to 25 dollars for a 10% discount
    When my purchase is for "<orderTotal>" dollars
    Then I should get a "<orderDiscount>" percent discount

    Examples:
      |orderTotal|orderDiscount|
      |10.00     |0            |
      |14.99     |0            |
      |15.00     |0            |
      |15.01     |10           |
      |20.00     |10           |
      |24.99     |10           |
      |25.00     |10           |

  Scenario Outline: purchase over 25 dollars for a 15% discount
    When my purchase is for "<orderTotal>" dollars
    Then I should get a "<orderDiscount>" percent discount

    Examples:
      |orderTotal|orderDiscount|
      |25.00     |10           |
      |35.00     |15           |


  Scenario Outline: purchase a combo and get and extra 5% discount
    When my purchase is for "<orderTotal>" dollars
    And my purchase "<hasDrinks>" has drinks
    And my purchase has "<tacoCount>" tacos
    Then I should get a "<orderDiscount>" percent discount

    Examples:
      |orderTotal|hasDrinks|tacoCount|orderDiscount|
      |5.00      |false    |        1|0            |
      |7.00      |false    |        2|0            |
      |9.00      |true     |        2|5            |
      |15.00     |true     |        2|5            |
      |25.00     |true     |        2|15           |
      |35.00     |true     |        2|20           |