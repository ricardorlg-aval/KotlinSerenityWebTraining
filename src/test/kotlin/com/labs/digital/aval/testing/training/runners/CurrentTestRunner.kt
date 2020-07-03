package com.labs.digital.aval.testing.training.runners

import io.cucumber.junit.CucumberOptions
import net.serenitybdd.cucumber.CucumberWithSerenity
import org.junit.runner.RunWith

@RunWith(CucumberWithSerenity::class)
@CucumberOptions(
        strict = true,
        features = ["src/test/resources/features"],
        tags = ["@current"],
        glue = ["com.labs.digital.aval.testing.training.features"]
)
class CurrentTestRunner {
}