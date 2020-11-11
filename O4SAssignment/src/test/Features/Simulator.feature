@Simulator

Feature: Simulator
  Scenario: Correct page layout and functionality
    Given Open browser to Index.html
    And Verify correct page layout
    Then Verify correct values for monthly installment for all months
