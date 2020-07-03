package com.labs.digital.aval.testing.training.screenplay.tasks

import com.labs.digital.aval.testing.training.screenplay.ui.TodoListItem
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.Tasks.instrumented
import net.serenitybdd.screenplay.actions.Click
import net.thucydides.core.annotations.Step

open class CompleteItem(private val itemName: String) : Task {
    @Step("{0} completes the item called: #itemName")
    override fun <T : Actor> performAs(actor: T) {
        actor.attemptsTo(
                Click.on(TodoListItem.COMPLETE_ITEM.of(itemName))
        )
    }

    companion object {
        fun called(itemName: String): CompleteItem = instrumented(CompleteItem::class.java, itemName)
    }
}