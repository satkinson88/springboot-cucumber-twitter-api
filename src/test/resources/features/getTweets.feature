@getTweets @allTests
Feature: Twitter GET Tweets


  Scenario Outline: Get tweet
    Given an authorized user
    When I get the tweets endpoint with id
      | <id> |
    Then after retrieving I get an "<status>" response
    And the GET response body contains "<expectedString>"
    Examples:
      | id                  | status | expectedString              |
      | 1644470992046104576 | OK     | \"text\":\"test api tweet\" |


  Scenario Outline: Get tweets
    Given an authorized user
    When I get the tweets endpoint with id
      | <id> |
    Then after retrieving I get an "<status>" response
    And the GET response body contains "<expectedString>"
    Examples:
      | id                                      | status | expectedString                    |
      | 1644470992046104576,1336443999071195137 | OK     | \"text\":\"API Test Tweet #6569\" |


  Scenario Outline: Verify json key/value pair
    Given an authorized user
    When I get the tweets endpoint with id
      | <id> |
    Then after retrieving I get an "<status>" response
    And the tweet response body contains "<id>" and "<text>"
    Examples:
      | id                  | status | text           |
      | 1644470992046104576 | OK     | test api tweet |