Feature: Transaction tests

  Scenario: Retrieve all transactions is status OK
    When I retrieve all transaction
    Then I get http status 200

  Scenario: Getting a list of all transactions
    When I retrieve all transaction
    Then I get a list of 3 transactions

  Scenario: Getting one guitar
    When I retrieve transaction with id 101
    Then The transaction amount is "10.00"

  Scenario: Creating a transaction
    When I post a transaction
    Then I get http status 201