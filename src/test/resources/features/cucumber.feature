Feature: Cucumber

  Scenario: A normal account
    Given I have written my username "Carre1234"
    Given I have written my email "fenisik483@karavic.com"
    Given I have written my password "Funkar1_"
    When I click the Sign Up button
    Then my account is made

  Scenario: A username with over 100 characters
    Given I have written my username "Jacob1234511111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"
    Given I have written my email "fuydimekna@vusra.com"
    Given I have written my password "Funkar1_1"
    When I click the Sign Up button
    Then I am shown a error

  Scenario: Username already in use
    Given I have written my username "Jacob"
    Given I have written my email "fuydimekna@vusra.com"
    Given I have written my password "Funkar1_1"
    When I click the Sign Up button
    Then I am shown a error

  Scenario: Email missing for the creation of the account
    Given I have written my username "Jacob15151222"
    Given I have written my email ""
    Given I have written my password "Funkar1_1"
    When I click the Sign Up button
    Then I am shown a error
