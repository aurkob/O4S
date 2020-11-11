@Heroes

Feature: Heroes
  Scenario: Correct fight settings - 2 heroes
    Given Get list of heroes
    And Choose and add distinct random hero
    Then Verify fight cannot start
    And Verify same hero cannot be added to fight
    And Choose and add distinct random hero
    Then Start fight
    Then Verify winner has highest power level
    And Reset battle

  Scenario: Correct fight settings - 3 heroes
    Given Get list of heroes
    And Choose and add distinct random hero
    And Choose and add distinct random hero
    And Verify same hero cannot be added to fight
    Then Choose and add distinct random hero
    And Verify hero cant be added anymore
    Then Start fight
    Then Verify winner has highest power level
