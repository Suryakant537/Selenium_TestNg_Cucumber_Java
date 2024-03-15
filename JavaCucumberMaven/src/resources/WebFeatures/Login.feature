@Web @Web-Firefox @Web-Edge @tags1
Feature: Login Functionality

  Background:
    Given User is on Login page

  Scenario Outline: Verify Sign-In Functionality for Valid User
    And User enters emailID "<username>"
    And User enters password "<password>"
    And User clicks on submit button
    Then User should be logged-in successfully

    Examples:
      | username | password |
      | Admin    | admin123|


  Scenario Outline: Verify Sign-In Functionality for invalid user
    And User enters emailID "<username>"
    And User enters password "<password>"
    And User clicks on submit button
    Then User should not logged in and proper message should be display

    Examples:
      | username | password |
      | Admin@Q  | admin123 |
      | Admin    | user@123 |


  Scenario: Verifying Creating Employee Functionality
    And user navigate to the View Employee List page
    When user enter the employee detail
    And user click on the Save button
    And user navigate to the PIM page
    Then the employee details should be displayed on the View Employee List page