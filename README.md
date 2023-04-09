# springboot-cucumber-twitter-api-test
Sample framework using springboot with cucumber to test the twitter api

### Overview

* Built to demonstrate the benefits of using springboot with cucumber in a test framework
* Spring uses dependency injection to share state between classes and step definitions allowing for greater control and handling of the http request
* Spring allows us to map the container to different environments. We can define in the application.properties the variables needed when executing tests in specific environments according our needs e.g. dev/test/live

### Spring Annotations

* @CucumberContextConfiguration and @SpringBootTest define your test entry point. Here we point to the profile classes for environment specific entry which we annotate with @Profile
* @ComponentScan is used in our profile class setup to define the directories to search for the classes to instantiate as part of the spring container.
* @Component annotation defines the classes to be part of the spring container
* @Autowired annotation ensures the instance of the class is instantiated as part of the container
* @Value annotation allows us to retrieve variables defined in the application.properties file. The file used will depend on which profile the container is being run with

### Running Tests
* Set the profile used for test execution in the application.properties file e.g. `spring.profiles.active=test`
* Set the bearer authentication token as a system property in -Dtoken= and choose your cucumber tag -Dcucumber.options= when executing the tests: `mvn clean test -Dcucumber.options="--tag @allTests" -Dtoken=""`
