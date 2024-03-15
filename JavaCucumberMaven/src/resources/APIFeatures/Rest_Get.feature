@API
Feature: Rest API GET Endpoint Functionality Scenarios

  Scenario Outline: GET new user data
    Given Get Call to "<url>"
    Then Response Code "<responseMessage>" is validated

    Examples:
      | url                | responseMessage |
      | api/users?page=2   | 200             |
      | /api/users/2       | 200             |
      | /api/users/23      | 404             |
      | /api/unknown/23    | 404             |



  Scenario Outline:  Verify Code
    Given Get Call to "<url>"
   Then Response Data Should Contain  "<first_name>"

    Examples:
      |         url         |   first_name |
      |   /api/users?page=2 |  Michael     |
      |   /api/users?page=2 |  Lindsay     |
      |   /api/users?page=2 |  Tobias      |
      |   /api/users?page=2 |  Byron       |
      |   /api/users?page=2 |  George      |
      |   /api/users?page=2 |  Rachel      |





