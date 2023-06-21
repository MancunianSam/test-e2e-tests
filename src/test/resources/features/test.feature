Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario: Sunday isn't Friday
    Given today is Sunday
    When I ask whether it's Friday yet
    Then I should be told "Nope"

  Scenario: Friday is Friday
    Given today is Friday
    When I ask whether it's Friday yet
    Then I should be told "Yep"

  Scenario: Tuesday is Tuesday
    Given today is Tuesday
    When I ask whether it's Tuesday yet
    Then I should be told "Yep"

  Scenario: Wednesday isn't Tuesday
    Given today is Wednesday
    When I ask whether it's Tuesday yet
    Then I should be told "Nope"
