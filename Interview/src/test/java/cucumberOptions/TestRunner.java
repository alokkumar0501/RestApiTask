package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/test/java/features"},glue= {"stepDefinations"},plugin="json:target/jsonReport/cucumber-report.json")

public class TestRunner extends AbstractTestNGCucumberTests{

}

