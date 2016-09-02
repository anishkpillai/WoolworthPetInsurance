package com.woolworthspetinsurance;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty","html:target/reports","json:target/cucumber.json"})

public class RunCukesTest {

}