package com.example.twitter.stepdefinitions;

import com.example.twitter.utils.CommonApiHelper;
import com.example.twitter.utils.PostApiHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class PostStepDefinitions {

    @Autowired
    private CommonApiHelper commonApiHelper;
    @Autowired
    private PostApiHelper postApiHelper;

    private final Logger log = LoggerFactory.getLogger(GetStepDefinitions.class);

    @When("I post the rule {string}")
    public void I_post_the_rule(String ruleFilePath) {
        postApiHelper.postRuleToTwitter(ruleFilePath);
    }

    @Then("^after posting I get (?:a|an) \"([^\"]*)\" response$")
    public void after_posting_I_get_a_response_status(String responseStatus) {
        Response response = postApiHelper.getResponse();
        commonApiHelper.getResponseStatus(responseStatus, response);
    }

    @And("the POST response body contains {string}")
    public void the_post_response_body_contains(String expectedString) {
        Response response = postApiHelper.getResponse();
        commonApiHelper.verifyStringFromResponse(expectedString, response);
    }

    @And("I store the {string} from the POST response body")
    public void I_store_the_key_from_the_post_response_body(String key) {
        Response response = postApiHelper.getResponse();
        commonApiHelper.setStoredValue(key, response);
    }
}
