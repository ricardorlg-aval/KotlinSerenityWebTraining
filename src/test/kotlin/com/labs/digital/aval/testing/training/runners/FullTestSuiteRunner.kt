package com.labs.digital.aval.testing.training.runners

import io.cucumber.junit.CucumberOptions
import net.serenitybdd.cucumber.CucumberWithSerenity
import org.junit.runner.RunWith

@RunWith(CucumberWithSerenity::class)
@CucumberOptions(
        strict = true,
        plugin = ["pretty"],
        features = ["src/test/resources/features"],
        glue = ["com.labs.digital.aval.testing.training.features"]
)
class FullTestSuiteRunner
