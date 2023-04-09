package com.example.twitter.stepdefinitions;

import com.example.twitter.utils.CommonApiHelper;
import com.example.twitter.utils.DeleteApiHelper;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class DeleteStepDefinitions {

    @Autowired
    private CommonApiHelper commonApiHelper;
    @Autowired
    private DeleteApiHelper deleteApiHelper;

    private final Logger logger = LoggerFactory.getLogger(DeleteStepDefinitions.class);

    @Then("I delete the rule using id")
    public void I_delete_the_rule_using_id() throws IOException {
        String ruleId = commonApiHelper.getStoredValue(0);
        deleteApiHelper.deleteRule(ruleId);
    }

    @Then("^after deleting I get (?:a|an) \"([^\"]*)\" response$")
    public void after_deleting_I_get_a_response_status(String responseStatus) {
        Response response = deleteApiHelper.getResponse();
        commonApiHelper.getResponseStatus(responseStatus, response);
    }

    @Then("the DELETE response body contains {string}")
    public void the_delete_response_body_contains(String expectedString) {
        Response response = deleteApiHelper.getResponse();
        commonApiHelper.verifyStringFromResponse(expectedString, response);
    }


}
