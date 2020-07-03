package com.labs.digital.aval.testing.training.screenplay.ui

import net.serenitybdd.screenplay.targets.Target

object TodoList {
    val WHAT_NEEDS_TO_BE_DONE: Target = Target.the("'What needs to be done?' field").locatedBy(".new-todo")
    val ITEMS: Target = Target.the("List of todo items").locatedBy(".view label")
    val FILTER: Target = Target.the("filter").locatedBy("//a[.='{0}']")
}