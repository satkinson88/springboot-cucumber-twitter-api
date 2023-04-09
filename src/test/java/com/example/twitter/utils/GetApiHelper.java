package com.example.twitter.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class GetApiHelper {

    @Autowired
    private CommonApiHelper commonApiHelper;

    @Value("${baseUri}")
    private String baseUri;

    private ObjectMapper mapper = new ObjectMapper();
    private Response response;
    private Logger logger = LoggerFactory.getLogger(GetApiHelper.class);

    public Response getResponse() {
        Response response = this.response;
        return response;
    }

    public void getTweetWithId(List<String> id) throws InterruptedException {
        int attempts = 0;
        do {
            try {
                String endpoint = "tweets";
                String token = commonApiHelper.getBearerToken();
                response = given()
                        .header("Authorization", token)
                        .header("Content-type", "application/json")
                        .queryParam("ids", id)
                        .when()
                        .get(baseUri + endpoint);
                break;
            } catch (Exception e) {
                e.printStackTrace();
                attempts++;
                Thread.sleep(1000);
            }
        } while (attempts < 3);
        if (attempts >= 3) {
            fail("Could not return response from api...");
        }
    }

    public void getRules() {
        String endpoint = "tweets/search/stream/rules";
        String token = commonApiHelper.getBearerToken();
        response = given()
                .header("Authorization", token)
                .header("Content-type", "application/json")
                .when()
                .get(baseUri + endpoint);
    }

    public void verifyTweetResponse(String id, String text) throws IOException {
        String actualBody = response.asString();
        String tweetFile = "getTweet.json";
        String expectedBody = commonApiHelper.readFromFile("responses", tweetFile);
        StringBuilder sb = new StringBuilder(expectedBody);
        commonApiHelper.resolvePlaceHolder(sb, "${idhistoryref}", id);
        commonApiHelper.resolvePlaceHolder(sb, "${idref}", id);
        commonApiHelper.resolvePlaceHolder(sb, "${textref}", text);
        JsonNode actual = mapper.readTree(actualBody);
        JsonNode expected = mapper.readTree(String.valueOf(sb));
        assertEquals(expected, actual);
        logger.info("The actual response body matches the expected response body");
    }

}
