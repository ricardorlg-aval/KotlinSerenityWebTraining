package com.labs.digital.aval.testing.training.features.steps

import com.labs.digital.aval.testing.training.screenplay.questions.TheItems
import com.labs.digital.aval.testing.training.screenplay.tasks.AddTodoItem
import com.labs.digital.aval.testing.training.screenplay.tasks.Filter
import com.labs.digital.aval.testing.training.screenplay.tasks.Start
import com.labs.digital.aval.testing.training.screenplay.ui.TodoList
import com.labs.digital.aval.testing.training.screenplay.utils.LAST_ADDED_ITEM
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen.seeThat
import net.serenitybdd.screenplay.RememberThat
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.actors.OnStage
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.collection.IsEmptyCollection
import org.hamcrest.collection.IsIterableContainingInOrder.contains

class TodoUserSteps {
    private val theActor: Actor
        get() = OnStage.theActorInTheSpotlight()

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
        theActor.attemptsTo(
                AddTodoItem.called(thingToDo),
                RememberThat.theValueOf(LAST_ADDED_ITEM).`is`(thingToDo)
        )
    }

    @When("she filters her list to show only {filterCriteria} tasks")
    fun `actor filters to show only tasks that matches criteria`(filterCriteria: String) {
        theActor.attemptsTo(
                Filter.by(filterCriteria)
        )
    }

    @When.Whens(
            When("he/she completes the task called {string}"),
            When("she has completed the task called {string}")
    )
    fun `actor completes the task`(toDoItemToComplete: String) {
        theActor.attemptsTo(
                Click.on(TodoList.completeTask(named = toDoItemToComplete))
        )
    }

    @Then("he/she should see the last added item in his/her list")
    fun `actor should see last thing to do in his todo list`() {
        val expectedThingToDo: String = theActor.recall(LAST_ADDED_ITEM)
        theActor.should(
                seeThat(
                        TheItems.displayed(), hasItem(expectedThingToDo)
                )
        )
    }

    @Then("his/her todo list should be empty")
    fun `actor should see todo list is empty`() {
        theActor.should(
                seeThat(
                        TheItems.displayed(), IsEmptyCollection.empty<String>()
                )
        )
    }

    @Then("his/her todo list should contain {todoItems}")
    fun `actor should see the added todo items`(expectedThingsToDo: List<String>) {
        theActor.should(
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

    @Then("{string} should be recorded in his/her list")
    fun `actor should see the thing to do in his todo list`(expectedThingToDo: String) {
        theActor.should(
                seeThat(
                        TheItems.displayed(), hasItem(expectedThingToDo)
                )
        )
    }
}
