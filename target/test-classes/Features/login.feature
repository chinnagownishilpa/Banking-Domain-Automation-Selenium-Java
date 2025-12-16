Feature: Login Feature

  Scenario: Login with valid credentials
    Given user launches ParaBank application
    When user logs in using valid credentials from excel
    Then user should login successfully
