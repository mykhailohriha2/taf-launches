Feature: User is able to remove a launch

  Background: User is logged into the report portal with demo data
    Given Demo data is created for the users and their projects
      | username  | userpassword  | project            |
      | testuser3 | testpassword3 | testuser3_personal |
    When User opens the Login page
    And User logs in with "testuser3" and "testpassword3"
    Then User should see the "Signed in successfully" message

  Scenario: Removing the first launch from the Launches page
    Given User navigate to the Launches page
    When User removes the launch with 0 index
    Then User should see the "Launch was deleted" message

  Scenario: Removing the second launch from the Launches page
    Given User navigate to the Launches page
    When User removes the launch with 1 index
    Then User should see the "Launch was deleted" message
