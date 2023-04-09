package com.example.twitter.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("dev")
@PropertySource("classpath:application_dev.properties")
@ComponentScan("com.example.twitter.*")
public class DevEnvSpringConfig {

}
