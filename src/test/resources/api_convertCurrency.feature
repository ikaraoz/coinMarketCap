@apiTests
Feature: Buy crypto currencies

  Scenario Outline: Successful fiat exchange and crypto-coin buy
    Given the user has 10000000 "<sourceFiatName>"
    And the user exchanges fiat "<targetFiatName>" with fiat "<sourceFiatName>"
    When the user buys crypto "<targetCryptoCurrencyName>" with fiat "<targetFiatName>"
    Then the error_code should be 0
    And data.name should match "<targetFiatName>"
    And "<targetCryptoCurrencyName>" price should not be null
    Examples:
      | sourceFiatName     | targetFiatName | targetCryptoCurrencyName |
      | Guatemalan Quetzal | Pound Sterling | Dogecoin                 |