import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/",
        plugin = {"pretty", "html:target/cucumber/report.html"},
        tags = "@allTests",
        glue = {"com.example.twitter.stepdefinitions", "com.example.twitter.configs"}
)

public class TestRunner {

}
