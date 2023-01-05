package stepDefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.junit.runner.Runner;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources"},
        plugin = {"pretty",
                "html:target/cucumber.html",
//                "de.monochromata.cucumber.report.PrettyReports:result/cucumber"}
//        tags = System.getProperty("environment")
        }
)
public class testRunner {
}
