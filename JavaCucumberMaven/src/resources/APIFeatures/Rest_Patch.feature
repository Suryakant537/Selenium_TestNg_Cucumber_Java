@API
Feature: Rest API PATCH Endpoint Functionality Scenarios

 Scenario Outline: Update a new user with Specific data

    Given the user is on the Home page of Website for Patch API
    When the user Updates user with the following job data
    Then the response should contain the following updated job "<job>" in the list
    Examples:
      | job              |
      | QaTester         |
      | QaTester1        |
      | QaTester2        |
      | QaTester3        |
      | QaTester4        |
      | QaTester5        |
      | QaTester6        |
      | QaTester7        |
      | QaTester8        |











#
#
