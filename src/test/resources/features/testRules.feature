@testRules @allTests
Feature: Twitter Test Rule


  Scenario Outline: Post then delete rule
    Given an authorized user
    When I post the rule "<rule>"
    Then after posting I get a "<postStatus>" response
    And the POST response body contains "<expectedString>"
    Then I store the "<key>" from the POST response body
    Then I delete the rule using id
    Then after deleting I get an "<deleteStatus>" response
    And the DELETE response body contains "<expectedString2>"
    Examples:
      | rule          | postStatus | expectedString | key     | deleteStatus | expectedString2 |
      | postRule.json | Created    | \"created\":1  | data.id | OK           | \"deleted\":1   |


  Scenario Outline: Get rule count
    Given an authorized user
    When I get the rules
    Then after retrieving I get an "<status>" response
    And the GET response body contains "<key>" with "<value>"
    Examples:
      | status | key               | value |
      | OK     | meta.result_count | 1     |