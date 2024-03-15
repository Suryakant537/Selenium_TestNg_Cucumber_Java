@API
Feature: Rest API DELETE Endpoint Functionality Scenarios

 Scenario Outline: Delete a new user with valid data

    Given the user is on the Home page of Website for Delete API
    When the user Deletes new user with the following data
    Then the response should not contain the following updated "<name>" in the list
    Examples:
      | name                |
      | Janet               |










#
