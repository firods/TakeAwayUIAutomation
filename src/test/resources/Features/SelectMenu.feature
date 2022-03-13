#Auther Shrikant Firodiya
#Date 10-03-2022

Feature: Select Food Category

  @smoke
  Scenario: verify select correct food category
    Given User completed the initial setup
    When User search and select restaurant name
    And User select menu "VEGETABLES" from navigation bar
    Then User completed teardown