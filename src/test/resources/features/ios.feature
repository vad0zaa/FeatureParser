@ios
Feature: iOS applications and browsers tests

  As a tester I would like to open all applications and browsers
  in order to test them.

  Scenario Outline: User opens native application and clicks button
    Given user opens <application>
    When user clicks the button
    Then the button should be still visible

    Examples:
      | application |
      | iOS app     |


  Scenario Outline: User opens Google homepage and does maths
    Given user opens Google homepage in <application>
    When user enters "<enter>" into the search input
    Then the calculator is displayed
    And result shown is <result>
    Examples:
      | application     | enter | result |
      | iOS browser     | 2 ^ 2 | 4      |