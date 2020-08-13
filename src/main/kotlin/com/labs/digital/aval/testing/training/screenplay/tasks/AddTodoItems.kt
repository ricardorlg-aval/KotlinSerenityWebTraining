package com.labs.digital.aval.testing.training.screenplay.tasks

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.Tasks.instrumented
import net.thucydides.core.annotations.Step

open class AddTodoItems(private val todoThings: List<String>) : Task {
    @Step("{0} adds the todo items: #todoThings")
    override fun <T : Actor> performAs(actor: T) {
        todoThings.forEach { todoItem ->
            actor.attemptsTo(
                    AddTodoItem.called(todoItem)
            )
        }
    }

    companion object {
        fun called(todoThings: List<String>): AddTodoItems = instrumented(AddTodoItems::class.java, todoThings)
    }
}
