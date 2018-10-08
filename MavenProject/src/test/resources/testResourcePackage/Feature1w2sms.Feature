Feature: 

  Scenario: title test
    Given launch the "chrome" browser
    Then title test validation "Free SMS"
    And close site

  Scenario Outline: Login validation
    Given launch the "chrome" browser
    Then enter the mobilenumber as "<m>"
    Then fill password as "<p>"
    
    Then validate output for criteria "<z>"
    And close site

    Examples: 
      | m          | p        | z            |
      | 9705021985 | vamsi431 | all_valid    |
      |            | vamsi431 | mbno_blank   |
      |  970501985 |          | pswd_blank   |
      | 9705021989 | vamsi431 | mbno_invalid |
      | 9705021985 | vamsi430 | pswd_invalid |
