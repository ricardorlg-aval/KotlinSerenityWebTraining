package com.labs.digital.aval.testing.training.screenplay.tasks

import com.labs.digital.aval.testing.training.screenplay.ui.ApplicationHomePage
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.Tasks.instrumented
import net.serenitybdd.screenplay.actions.Open
import net.serenitybdd.screenplay.conditions.Check
import net.thucydides.core.annotations.Step

open class Start(private val items: List<String>, private val todoListDescription: String) : Task {
    lateinit var applicationHomePage: ApplicationHomePage

    @Step("{0} starts with #todoListDescription")
    override fun <T : Actor> performAs(actor: T) {
        actor.attemptsTo(
                Open.browserOn().the(applicationHomePage),
                Check.whether(items.isNotEmpty())
                        .andIfSo(
                                AddTodoItems.called(items)
                        )
        )
    }

    companion object {
        fun withAnEmptyTodoList(): Start = instrumented(Start::class.java, emptyList<String>(), "no items")
        fun withATodoListContaining(items: List<String>): Start = instrumented(Start::class.java, items, "a todo list containing ${items.joinToString()}")
    }
}