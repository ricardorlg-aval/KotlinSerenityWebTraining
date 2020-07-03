package com.labs.digital.aval.testing.training.features.steps

import com.labs.digital.aval.testing.training.screenplay.exceptions.MissingTodoItemsException
import com.labs.digital.aval.testing.training.screenplay.model.TodoStatusFilter
import com.labs.digital.aval.testing.training.screenplay.questions.TheTodoItems
import com.labs.digital.aval.testing.training.screenplay.tasks.AddATodoItem
import com.labs.digital.aval.testing.training.screenplay.tasks.CompleteItem
import com.labs.digital.aval.testing.training.screenplay.tasks.FilterItems
import com.labs.digital.aval.testing.training.screenplay.tasks.Start
import io.cucumber.java.Before
import io.cucumber.java.ParameterType
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen.seeThat
import net.serenitybdd.screenplay.actors.OnStage
import net.serenitybdd.screenplay.actors.OnlineCast
import org.hamcrest.Matchers
import org.hamcrest.collection.IsEmptyCollection

class TodoUserSteps {

    private val currentActor: Actor
        get() = OnStage.theActorInTheSpotlight()

    @Before
    fun `set the stage`() {
        OnStage.setTheStage(OnlineCast())
    }

    @ParameterType(value = "(.*)", name = "actor")
    fun actorConverter(actorName: String): Actor = OnStage.theActorCalled(actorName)

    @ParameterType(value = "(.*)", name = "filterStatus")
    fun filterStatusConverter(statusName: String) = TodoStatusFilter.statusFilterFromName(statusName)

    @ParameterType(value = "(.*)", name = "todoItems")
    fun todoItems(items: String) = items.split(",").map(String::trim)

    @Given("that {actor} has an empty todo list")
    fun `actor has an empty todo list`(actor: Actor) {
        actor.wasAbleTo(
                Start.withAnEmptyTodoList()
        )
    }

    @Given("that {actor} has a todo list containing {todoItems}")
    fun `actor has a list of tems in his todo list`(actor: Actor, items: List<String>) {
        actor.wasAbleTo(
                Start.withATodoListContaining(items)
        )
    }

    @When("she/he completes the task called {string}")
    @And("she/he has completed the task called {string}")
    fun `actors has completed a task called`(itemName: String) {
        currentActor.has(
                CompleteItem.called(itemName)
        )
    }

    @When("he/she adds {string} to his/her list")
    fun `actor adds an item to his todo list`(thingToDo: String) {
        currentActor.attemptsTo(
                AddATodoItem.called(thingToDo)
        )
    }

    @When("she/he filters her/his list to show only {filterStatus} tasks")
    fun `actor filters his todo list to only show completed tasks`(status: TodoStatusFilter) {
        currentActor.attemptsTo(
                FilterItems.toShow(status)
        )
    }

    @Then("{string} should be recorded in his/her list")
    fun `the added thing should be in his todo list`(addedThingToDo: String) {
        currentActor.should(seeThat(TheTodoItems.displayed(), Matchers.hasItem(addedThingToDo))
                .orComplainWith(MissingTodoItemsException::class.java, "The todo item $addedThingToDo was not found in the user todo list")
        )
    }

    @Then("her/his todo list should contain {todoItems}")
    fun `actor todo list should contains the initial items`(thingsToDo: List<String>) {
        currentActor.should(seeThat(TheTodoItems.displayed(), Matchers.contains(*thingsToDo.toTypedArray()))
                .orComplainWith(MissingTodoItemsException::class.java, "The todo item $thingsToDo was not found in the user todo list")
        )
    }

    @Then("{actor}'s todo list should contain {todoItems}")
    fun `actor todo list should contains the initial items`(actor: Actor, thingsToDo: List<String>) {
        actor.should(seeThat(TheTodoItems.displayed(), Matchers.contains(*thingsToDo.toTypedArray()))
                .orComplainWith(MissingTodoItemsException::class.java, "The todo item $thingsToDo was not found in the user todo list")
        )
    }

    @Then("her/his todo list should be empty")
    fun `the actor todo list should be empty`() {
        currentActor.should(
                seeThat(TheTodoItems.displayed(), IsEmptyCollection.empty<String>())
        )
    }
}