package com.labs.digital.aval.testing.training.screenplay.tasks

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.Tasks.instrumented
import net.thucydides.core.annotations.Step

open class AddTodoItems(private val items: List<String>) : Task {
    @Step("{0} adds the todo items: #items")
    override fun <T : Actor> performAs(actor: T) {
        items.forEach { todo ->
            actor.attemptsTo(AddATodoItem.called(todo))
        }
    }

    companion object {
        fun called(items: List<String>): AddTodoItems = instrumented(AddTodoItems::class.java, items)
    }
}