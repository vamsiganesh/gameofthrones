Feature: Logout

  Scenario: Logout
    Given launch the "chrome " browser
    When login with valid data
      | mbno       | pswd     |
      | 9705021985 | vamsi431 |
    Then dologout
    Then sendsmspage will be displayed
    Then dologout
    Then homepage will be reopened
    And close site
