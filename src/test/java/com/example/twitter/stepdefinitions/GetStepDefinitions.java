package com.example.twitter.stepdefinitions;

import com.example.twitter.utils.GetApiHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.twitter.utils.CommonApiHelper;

import java.io.IOException;
import java.util.List;


public class GetStepDefinitions {

    @Autowired
    private CommonApiHelper commonApiHelper;
    @Autowired
    private GetApiHelper getApiHelper;

    private final Logger logger = LoggerFactory.getLogger(GetStepDefinitions.class);

    @When("I get the tweets endpoint with id(s)")
    public void I_get_the_tweets_endpoint_with_id(List<String> id) throws InterruptedException {
        getApiHelper.getTweetWithId(id);
    }

    @Then("^after retrieving I get (?:a|an) \"([^\"]*)\" response$")
    public void after_retrieving_I_get_a_response_status(String responseStatus) {
        Response response = getApiHelper.getResponse();
        commonApiHelper.getResponseStatus(responseStatus, response);
    }

    @And("the GET response body contains {string}")
    public void the_get_response_body_contains(String expectedString) {
        Response response = getApiHelper.getResponse();
        commonApiHelper.verifyStringFromResponse(expectedString, response);
    }

    @And("the tweet response body contains {string} and {string}")
    public void the_tweet_response_body_contains_id_and_text(String value, String value2) throws IOException {
        getApiHelper.verifyTweetResponse(value, value2);
    }

    @When("I get the rules")
    public void I_get_the_rules() {
        getApiHelper.getRules();
    }

    @And("the GET response body contains {string} with {string}")
    public void the_get_response_body_contains_key_with_value(String key, String value) {
        Response response = getApiHelper.getResponse();
        commonApiHelper.verifyValueFromKey(key, value, response);
    }

}
