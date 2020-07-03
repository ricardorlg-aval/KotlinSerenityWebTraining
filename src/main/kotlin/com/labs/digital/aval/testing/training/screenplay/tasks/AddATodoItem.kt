package com.labs.digital.aval.testing.training.screenplay.tasks

import com.labs.digital.aval.testing.training.screenplay.ui.TodoList
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.Tasks.instrumented
import net.serenitybdd.screenplay.actions.Enter
import net.thucydides.core.annotations.Step
import org.openqa.selenium.Keys

open class AddATodoItem(private val thingToDo: String) : Task {
    @Step("{0} adds a todo item called: #thingToDo")
    override fun <T : Actor> performAs(actor: T) {
        actor.attemptsTo(
                Enter.theValue(thingToDo)
                        .into(TodoList.WHAT_NEEDS_TO_BE_DONE)
                        .thenHit(Keys.RETURN)
        )
    }

    companion object {
        fun called(thingToDo: String): AddATodoItem = instrumented(AddATodoItem::class.java, thingToDo)
    }
}