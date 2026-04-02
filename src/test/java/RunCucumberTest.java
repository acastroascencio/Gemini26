package org.example;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@Listeners(org.example.ExtentReportListener.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "org.example",
        plugin = {"pretty"}
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}