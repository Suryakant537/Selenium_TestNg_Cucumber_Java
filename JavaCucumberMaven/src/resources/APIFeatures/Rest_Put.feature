@API
Feature: Rest API PUT Endpoint Functionality Scenarios

 Scenario Outline: Update a new user with valid data

    Given the user is on the Home page of Website
    When the user Updates  new user with the following data
    Then the response should contain the following updated "<name>" in the list
    Examples:
      | name                |
      | NewUserName         |
      | NewUserName1        |
      | NewUserName2        |
      | NewUserName3        |
      | NewUserName4        |
      | NewUserName5        |
      | NewUserName6        |
      | NewUserName7        |
      | NewUserName8        |










