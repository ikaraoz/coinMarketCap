Feature: Filtering ui and extracting data for comparison

  Scenario Outline: User can successfully extract and compare data successfully
    Given the user visits "https://coinmarketcap.com/"
    And filters rows by 20
    And captures page contents with information "<information>"
    And filters by Algorithm - "PoW"
    And adds filters
    And toggles "Mineable"
    And selects "All Cryptocurrencies"
    And selects "Coins"
    And sets minimum value to 100 and maximum value to 10000
    Then the number of rows should not be more than 20
    Examples:
      | information         | minimum | maximum |
      | name,price,position | 100     | 10000   |
