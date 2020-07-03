package com.labs.digital.aval.testing.training.screenplay.tasks

import com.labs.digital.aval.testing.training.screenplay.model.TodoStatusFilter
import com.labs.digital.aval.testing.training.screenplay.ui.TodoList
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.Tasks.instrumented
import net.serenitybdd.screenplay.actions.Click

open class FilterItems(private val itemStatus: TodoStatusFilter) : Task {
    override fun <T : Actor> performAs(actor: T) {
        actor.attemptsTo(
                Click.on(TodoList.FILTER.of(itemStatus.statusName()).called("Filter by $itemStatus items"))
        )
    }

    companion object {
        fun toShow(itemStatus: TodoStatusFilter): FilterItems = instrumented(FilterItems::class.java, itemStatus)
    }
}