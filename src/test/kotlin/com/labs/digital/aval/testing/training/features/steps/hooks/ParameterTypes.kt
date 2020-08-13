package com.labs.digital.aval.testing.training.features.steps.hooks

import io.cucumber.java.ParameterType
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.actors.OnStage

class ParameterTypes {
    @ParameterType(value = "(.*)", name = "actor")
    fun actor(actorName: String): Actor = OnStage.theActorCalled(actorName).describedAs("A test user")

    @ParameterType(value = "(.*)", name = "todoItems")
    fun todoItems(items: String): List<String> = items.split(",").map(String::trim)

    @ParameterType(value = "(.*)", name = "filterCriteria")
    fun filterCriteria(filterCriteria: String): String = filterCriteria
}
