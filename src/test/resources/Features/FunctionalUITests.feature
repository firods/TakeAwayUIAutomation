#Auther Shrikant Firodiya
#Date 10-03-2022
Feature: Food Delivery Feature

  @smoke
  Scenario: verify restaurant not deliver food when order is not in same city
    Given User completed the initial setup
    When User search and select restaurant name
    And User select menu "Burgers" from navigation bar
    And User add order into basket
    And User enter incorrect city information
    And User enter personal information
    Then User submit the order
    And User able to see delivery error message
    Then User completed teardown

  @multi_language @smoke
  Scenario Outline: verify select language from language list
    Given User completed the initial setup
    And User select <language> language
    When User search and select restaurant name
    Then User completed teardown

    Examples: 
      | language   |
      | Nederlands |
      | Deutsch    |

  @multi_country @smoke
  Scenario Outline: verify select country from country list
    Given User completed the initial setup
    And User select <country> country
    Then User completed teardown

    Examples: 
      | country |
      | Germany |
      | Canada  |
      | France  |
