package com.example.twitter.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class CommonApiHelper {

    private String bearerToken;
    private ResponseBody responseBody;
    public ArrayList<String> storedValue = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(CommonApiHelper.class);

    public String getBearerToken() {
        String bearerToken = this.bearerToken;
        return bearerToken;
    }

    public void getAuthorizedUser() {
        bearerToken = System.getProperty("token");
        logger.info(bearerToken);
    }

    public void getResponseStatus(String responseSatus, Response response) {
        int statusCode = 0;
        if (responseSatus.equalsIgnoreCase("OK")) {
            statusCode = 200;
        } else if (responseSatus.equalsIgnoreCase("Created")) {
            statusCode = 201;
        } else if (responseSatus.equalsIgnoreCase("Accepted")) {
            statusCode = 202;
        }
        Assert.assertEquals(statusCode, response.getStatusCode());
        logger.info("Status = " + response.getStatusCode());
    }

    public void verifyStringFromResponse(String expectedString, Response response) {
        String bodyAsString;
        responseBody = response.getBody();
        bodyAsString = responseBody.asString();
        Assert.assertTrue(bodyAsString.contains(expectedString));
        logger.info("Json body contains " + expectedString);
    }

    public String readFromFile(String fileType, String fileName) {
        final String filePath = fileType + "/" + fileName;
        String body = null;
        try {
            body = new String(Files.readAllBytes(Paths.get("src/test/resources/" + filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Returning content from " + filePath);
        return body;
    }

    public void resolvePlaceHolder(StringBuilder body, String placeHolder, String value) {
        int start = body.indexOf(placeHolder);
        if (start < 0) {
            return;
        }
        int end = start + placeHolder.length();
        body.replace(start, end, value);
        logger.info(String.format("Replacing %s with %s ", placeHolder, value));
    }

    public void setStoredValue(String key, Response response) {
        String responseBody = response.asString();
        String responseValue = new JsonPath(responseBody).get(key).toString();
        String trimmedResponseValue = responseValue.replace("[", "").replace("]", "");
        storedValue.add(trimmedResponseValue);
        logger.info("Stored " + trimmedResponseValue + " for later retrieval...");
    }

    public String getStoredValue(int index) {
        return storedValue.get(index);
    }

    public void verifyValueFromKey(String key, String expectedValue, Response response) {
        String responseBody = response.asString();
        String actualValue = new JsonPath(responseBody).get(key).toString();
        Assert.assertEquals(expectedValue, actualValue);
        logger.info("Expected value " + expectedValue + " matches actual value " + actualValue);
    }

}
