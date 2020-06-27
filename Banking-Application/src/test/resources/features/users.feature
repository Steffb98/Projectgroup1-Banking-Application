Feature: Users tests

  Scenario: Retrieve all users is status OK
    When I retrieve all users
    Then I get http status 200

  Scenario: Retrieve one user is status OK
    When I retrieve user with id 50
    Then I get http status 200

  Scenario: Retrieve wrong user is status BAD_REQUEST
    When I retrieve user with id 5
    Then I get http status 404

  Scenario: Getting a list of all users
    When I retrieve all users
    Then I get a list of 4 users

  Scenario: Creating an user
    When I post an user
    Then I get http status 201

  Scenario: Changing the activity status of a user
    When I Change the status of an user with id 50
    Then I get http status 200

  Scenario: Changing the activity status of a user that does not exist
    When I Change the status of an user with id 5
    Then I get http status 400

  Scenario: Retrieve user with name is status OK
    When I retrieve user with name "Hayo"
    Then I get http status 200

  Scenario: Update email and password from a user
    When I update email and password from user with id 50 email "bert@gmail.com" password "ernie"
    Then I get http status 200