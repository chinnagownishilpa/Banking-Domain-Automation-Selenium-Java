Feature: Login and Transfer Funds

  Scenario: Register, login and transfer money successfully
    Given user launches ParaBank application
    And user registers a new account
    And user handles post registration error if present
    And user navigates to Transfer Funds page
    When user transfers "100"
    Then transfer should be successful
