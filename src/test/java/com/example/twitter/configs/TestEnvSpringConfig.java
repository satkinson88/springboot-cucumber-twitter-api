package com.example.twitter.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("test")
@PropertySource("classpath:application_test.properties")
@ComponentScan("com.example.twitter.*")
public class TestEnvSpringConfig {

}
