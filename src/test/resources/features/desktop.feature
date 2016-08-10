@desktop
Feature: Desktop browsers tests

  As a tester I would like to open all browsers
  in order to test them.

  Scenario Outline: User opens Google homepage and does maths
    Given user opens Google homepage in <application>
    When user enters "<enter>" into the search input
    Then the calculator is displayed
    And result shown is <result>
    Examples:
      | application     | enter | result |
      | desktop browser | 2 + 2 | 4      |