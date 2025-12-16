Feature: Customer registration, login and fund transfer

  Scenario: Register a new customer
    Given user launches ParaBank application
    When user registers a new customer
    Then customer should be registered successfully

  Scenario: Login with registered customer
    Given user launches ParaBank application
    When user logs in with registered credentials
    And user handles post login error if present
    Then user should be logged in successfully

  Scenario: Transfer funds for logged in customer
    Given user launches ParaBank application
    When user logs in with registered credentials
    And user navigates to Transfer Funds page
    And user transfers "100"
    Then transfer should be successful
