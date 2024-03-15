@API
Feature: Rest API POST Endpoint Functionality Scenarios

 Scenario Outline: Create a new user with valid data

    Given the user is on the Home page
    When the user creates a new user with the following data:
    Then the response should contain the following "<email>" in the list
    Examples:
      | email               |
      | eve.holt@reqres.in  |
      | eve1.holt@reqres.in |
      | eve2.holt@reqres.in |
      | eve3.holt@reqres.in |
      | eve4.holt@reqres.in |
      | eve5.holt@reqres.in |
      | eve6.holt@reqres.in |
      | eve7.holt@reqres.in |










