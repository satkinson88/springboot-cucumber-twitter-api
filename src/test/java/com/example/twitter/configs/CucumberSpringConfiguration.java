package com.example.twitter.configs;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = {TestEnvSpringConfig.class, DevEnvSpringConfig.class})
public class CucumberSpringConfiguration {

}
