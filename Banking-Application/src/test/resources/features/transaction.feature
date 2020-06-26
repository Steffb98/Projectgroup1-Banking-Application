Feature: Transaction tests

  Scenario: Retrieve all transactions is status OK
    When I retrieve all transaction
    Then I get http status 200

  Scenario: Retrieve one transaction is status OK
    When I retrieve transaction with id 100
    Then I get http status 200

  Scenario: Retrieve wrong transaction is status BAD_REQUEST
    When I retrieve transaction with id 5
    Then I get http status 404

  Scenario: Getting a list of all transactions
    When I retrieve all transaction
    Then I get a list of 2 transactions

  Scenario: Retrieve transactions from user
    When I retrieve transaction from user 51
    Then I get http status 200

  Scenario: Retrieve transaction from account
    When I retrieve transaction from account "NL89%20INHO%209673%207866%2032"
    Then I get http status 200

  Scenario: Creating a transaction
    When I post a transaction
    Then I get http status 201