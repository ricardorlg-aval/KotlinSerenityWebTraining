package com.labs.digital.aval.testing.training.features.steps.hooks

import io.cucumber.java.Before
import io.github.bonigarcia.wdm.WebDriverManager
import net.serenitybdd.screenplay.actors.OnStage
import net.serenitybdd.screenplay.actors.OnlineCast
import net.serenitybdd.screenplay.actors.Stage

class PrepareStageSteps {
    @Before(order = 0)
    fun `setup webdriver`(): Unit = WebDriverManager.chromedriver().setup()

    @Before
    fun `set the stage`(): Stage = OnStage.setTheStage(OnlineCast())
}
