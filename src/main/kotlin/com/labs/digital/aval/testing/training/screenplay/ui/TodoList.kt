package com.labs.digital.aval.testing.training.screenplay.ui

import net.serenitybdd.screenplay.targets.Target

object TodoList {
    val WHAT_NEEDS_TO_BE_DONE: Target = Target.the("'What needs to be done?' field").locatedBy(".new-todo")
    val ITEMS: Target = Target.the("List of todo items").locatedBy("ul.todo-list li label")

    fun completeTask(named: String): Target =
            Target.the("Task to complete").locatedBy("//label[contains(text(), '$named')]/preceding-sibling::input")

    fun filterCriteriaNamed(filterCriteria: String): Target =
            Target.the("Filter criteria named $filterCriteria").locatedBy("//a[text() = '$filterCriteria']")
}
