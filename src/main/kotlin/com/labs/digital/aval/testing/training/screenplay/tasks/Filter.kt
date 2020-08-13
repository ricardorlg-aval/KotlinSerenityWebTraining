package com.labs.digital.aval.testing.training.screenplay.tasks

import com.labs.digital.aval.testing.training.screenplay.ui.TodoList
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.Tasks.instrumented
import net.serenitybdd.screenplay.actions.Click
import net.thucydides.core.annotations.Step

open class Filter(private val filterCriteria: String) : Task {
    @Step("{0} filters by criteria: #filterCriteria")
    override fun <T : Actor> performAs(actor: T) {
        actor.attemptsTo(
                Click.on(TodoList.filterCriteriaNamed(filterCriteria))
        )
    }

    companion object {
        fun by(criteria: String): Filter = instrumented(Filter::class.java, criteria)
    }
}
