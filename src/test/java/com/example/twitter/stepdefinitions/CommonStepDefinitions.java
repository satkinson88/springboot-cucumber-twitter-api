package com.example.twitter.stepdefinitions;

import com.example.twitter.utils.CommonApiHelper;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonStepDefinitions {

    @Autowired
    private CommonApiHelper commonApiHelper;

    @Given("^an authorized user$")
    public void an_authorized_user(){
        commonApiHelper.getAuthorizedUser();
    }

}
