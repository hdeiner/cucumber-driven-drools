package com.tacoshop.test.controller;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
//      dryRun   = false,
//      strict = true,
//      tags     = "",
//      monochrome = false,
        features = { "src/test/java/com/tacoshop/test/controller/features" },
        glue     = { "com.tacoshop.test.controller" },
        plugin   = { "pretty", "html:target/controller-reports/controller-html-report", "json:target/controller-reports/controller-json-report.json" }
)

public class CucumberTests {

}