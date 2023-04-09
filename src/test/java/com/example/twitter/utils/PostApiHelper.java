package com.example.twitter.utils;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import static io.restassured.RestAssured.given;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class PostApiHelper {

    @Autowired
    CommonApiHelper commonApiHelper;

    @Value("${baseUri}")
    private String baseUri;
    private Response response;
    private Logger logger = LoggerFactory.getLogger(PostApiHelper.class);

    public Response getResponse() {
        Response response = this.response;
        return response;
    }

    public void postRuleToTwitter(String ruleFile) {
        String endpoint = "tweets/search/stream/rules";
        String payload = commonApiHelper.readFromFile("requests", ruleFile);
        String token = commonApiHelper.getBearerToken();
        response = given()
                .header("Authorization", token)
                .header("Content-type", "application/json")
                .body(payload)
                .when()
                .post(baseUri + endpoint);
    }


}
