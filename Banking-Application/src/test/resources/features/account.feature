Feature: Account tests

  Scenario: Retrieve one account is status OK
    When I retrieve account with id "NL62%20INHO%207604%201571%2015"
    Then I get http status 200

  Scenario: Retrieve wrong user is status BAD_REQUEST
    When I retrieve account with id "NL06%20INHO%200775%203131%2085"
    Then I get http status 404

  Scenario: Creating an account
    When I post an account
    Then I get http status 201

  Scenario: Retrieve accounts with userId is status OK
    When I retrieve account with userId 50
    Then I get http status 200
    And I get a list of 2 accounts