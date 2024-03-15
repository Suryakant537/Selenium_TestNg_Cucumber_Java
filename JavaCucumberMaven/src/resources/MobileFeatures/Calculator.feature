@Android
Feature: Calculator Functionality

  Scenario Outline: Addition of two numbers
    Given User opened the calculator app
    When User enters first number "<FirstNumber>"
    And User press the plus button
    And User enter Second number "<secondNumber>"
    And User press equal button
    Then User gets the result "<result>"
    Examples:
      | FirstNumber |secondNumber |result|
      | 5           | 8           |13   |
      | 9           |3            |12   |

  Scenario Outline: Subtraction of two numbers
    Given User opened the calculator app
    When User enters first number "<FirstNumber>"
    And User press the subtraction button
    And User enter Second number "<secondNumber>"
    And User press equal button
    Then User gets the result "<result>"
    Examples:
      | FirstNumber |secondNumber |result|
      | 9           |2            |7   |
      | 8           |3            |5   |

  Scenario Outline: Multiplication of two numbers
    Given User opened the calculator app
    When User enters first number "<FirstNumber>"
    And User press the multiplication button
    And User enter Second number "<secondNumber>"
    And User press equal button
    Then User gets the result "<result>"
    Examples:
      | FirstNumber |secondNumber |result|
      | 9           |2            |18   |
      | 8           |3            |24   |

  Scenario Outline: Division of two numbers
    Given User opened the calculator app
    When User enters first number "<FirstNumber>"
    And User press the division button
    And User enter Second number "<secondNumber>"
    And User press equal button
    Then User gets the result "<result>"
    Examples:
      | FirstNumber |secondNumber |result|
      | 9           | 3           |3   |
      | 8           |4            |2   |