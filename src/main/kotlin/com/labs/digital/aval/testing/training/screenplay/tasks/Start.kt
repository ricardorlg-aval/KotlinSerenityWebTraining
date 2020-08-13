package com.labs.digital.aval.testing.training.screenplay.tasks

import com.labs.digital.aval.testing.training.screenplay.ui.ApplicationHomePage
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.Tasks.instrumented
import net.serenitybdd.screenplay.actions.Open
import net.serenitybdd.screenplay.conditions.Check
import net.thucydides.core.annotations.Step

open class Start(private val todoItems: List<String>, private val todoListDescription: String) : Task {
    lateinit var applicationHomePage: ApplicationHomePage

    @Step("{0} starts with #todoListDescription")
    override fun <T : Actor> performAs(actor: T) {
        actor.attemptsTo(
                Open.browserOn().the(applicationHomePage),
                Check.whether(todoItems.isNotEmpty()).andIfSo(
                        AddTodoItems.called(todoItems)
                )
        )
    }

    companion object {
        fun withAnEmptyTodoList(): Start = instrumented(Start::class.java, emptyList<String>(), "an empty todo list")

        fun withATodoListContaining(thingsToDo: List<String>): Start = instrumented(Start::class.java, thingsToDo, "a todo list containing ${thingsToDo.joinToString()}")
    }
}
