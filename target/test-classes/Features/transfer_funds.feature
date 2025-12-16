Feature: Transfer Funds

  Scenario: Transfer money between accounts
    Given user is logged into ParaBank
    When user transfers amount using excel data
    Then transfer should be successful
