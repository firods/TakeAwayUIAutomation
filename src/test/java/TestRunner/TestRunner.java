package TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.*;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:/Users/Shrikant/eclipse-workspace/TakeAwayUIAutomation/src/test/resources/Features",glue= {"StepDefinations"},
plugin= {"json:target/cucumber.json"})
public class TestRunner {

}
