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
public class DeleteApiHelper {

    @Autowired
    private CommonApiHelper commonApiHelper;

    @Value("${baseUri}")
    private String baseUri;
    private Response response;
    private Logger logger = LoggerFactory.getLogger(DeleteApiHelper.class);

    public Response getResponse() {
        Response response = this.response;
        return response;
    }

    public void deleteRule(String ruleId) {
        String endpoint = "tweets/search/stream/rules";
        String token = commonApiHelper.getBearerToken();
        String ruleFile = "deleteRuleUsingId.json";
        String payload = commonApiHelper.readFromFile("requests", ruleFile);
        StringBuilder payloadWithId = new StringBuilder(payload);
        commonApiHelper.resolvePlaceHolder(payloadWithId, "${idref}", ruleId);
        String finalPayload = payloadWithId.toString();

        response = given()
                .header("Authorization", token)
                .header("Content-type", "application/json")
                .body(finalPayload)
                .when()
                .post(baseUri + endpoint);
    }

}
