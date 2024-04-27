Feature: User is able to login

  Scenario Outline: Users with different permissions are able to login
    Given User opens the Login page
    When User enters "<username>" in login field
    And User enters "<password>" in password field
    And User click on the login button
    Then User should see the "Signed in successfully" message

    Examples:
      | username   | password |
      | default    | 1q2w3e   |
      | superadmin | erebus   |
