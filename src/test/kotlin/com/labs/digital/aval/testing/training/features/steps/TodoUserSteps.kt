package com.labs.digital.aval.testing.training.features.steps

import com.labs.digital.aval.testing.training.screenplay.questions.TheItems
import com.labs.digital.aval.testing.training.screenplay.tasks.AddTodoItem
import com.labs.digital.aval.testing.training.screenplay.tasks.Start
import com.labs.digital.aval.testing.training.screenplay.utils.LAST_ADDED_ITEM
import io.cucumber.java.Before
import io.cucumber.java.ParameterType
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen.seeThat
import net.serenitybdd.screenplay.RememberThat
import net.serenitybdd.screenplay.actors.OnStage
import net.serenitybdd.screenplay.actors.OnlineCast
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.collection.IsIterableContainingInOrder.contains

class TodoUserSteps {

    private val currentActor: Actor
        get() = OnStage.theActorInTheSpotlight()

    @Before
    fun `set the stage`() {
        OnStage.setTheStage(OnlineCast())
    }

    @ParameterType(value = "(.*)", name = "actor")
    fun actorConverter(actorName: String): Actor = OnStage.theActorCalled(actorName).describedAs("A test user")

    @ParameterType(value = "(.*)", name = "todoItems")
    fun todoItemsConverter(items: String) = items.split(",").map(String::trim)

    @Given("that {actor} has an empty todo list")
    fun `actor starts with an empty todo list`(actor: Actor) {
        actor.wasAbleTo(
                Start.withAnEmptyTodoList()
        )
    }

    @Given("that {actor} has a todo list containing {todoItems}")
    fun `actor starts with a todo list containing some pending tasks`(actor: Actor, thingsToDo: List<String>) {
        actor.wasAbleTo(
                Start.withATodoListContaining(thingsToDo)
        )
    }

    @When("he/she adds {string} to his/her list")
    fun `actor adds a todo thing to his list`(thingToDo: String) {
        currentActor.attemptsTo(
                AddTodoItem.called(thingToDo),
                RememberThat.theValueOf(LAST_ADDED_ITEM).`is`(thingToDo)
        )
    }

    @Then("{string} should be recorded in his/her list")
    fun `actor should see the thing to do in his todo list`(expectedThingToDo: String) {
        currentActor.should(
                seeThat(
                        TheItems.displayed(), hasItem(expectedThingToDo)
                )
        )
    }

    @Then("he/she should see the last added item in his/her list")
    fun `actor should see last thing to do in his todo list`() {
        val expectedThingToDo: String = currentActor.recall(LAST_ADDED_ITEM)
        currentActor.should(
                seeThat(
                        TheItems.displayed(), hasItem(expectedThingToDo)
                )
        )
    }

    @Then("her/his todo list should contain {todoItems}")
    fun `actor should see the added todo items`(expectedThingsToDo: List<String>) {
        currentActor.should(
                seeThat(
                        TheItems.displayed(), contains(*expectedThingsToDo.toTypedArray())
                )
        )
    }

    @Then("{actor}'s todo list should contain {todoItems}")
    fun `actor should see the added todo items`(actor: Actor, expectedThingsToDo: List<String>) {
        actor.should(
                seeThat(
                        TheItems.displayed(), contains(*expectedThingsToDo.toTypedArray())
                )
        )
    }
}