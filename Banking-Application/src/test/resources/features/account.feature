Feature: Account tests

  Scenario: Retrieve one account is status OK
    When I retrieve account with id "NL26%20INHO%204265%209022%2078"
    Then I get http status 200

  Scenario: Retrieve wrong user is status BAD_REQUEST
    When I retrieve account with id "NL06%20INHO%200775%203131%2085"
    Then I get http status 404

  Scenario: Creating an account
    When I post an account
    Then I get http status 201

  Scenario: Changing the activity from an account
    When I change the activity from an account with id "NL36%20INHO%201491%206970%2002"
    Then I get http status 200

  Scenario: Changing the activity from an account that does not exist
    When I change the activity from an account with id "NL11%20INHO%201111%201111%2011"
    Then I get http status 404

  Scenario: Retrieve accounts with userId is status OK
    When I retrieve account with userId 50
    Then I get http status 200
    And I get a list of 2 accounts