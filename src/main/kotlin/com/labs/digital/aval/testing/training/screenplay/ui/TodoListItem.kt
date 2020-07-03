package com.labs.digital.aval.testing.training.screenplay.ui

import net.serenitybdd.screenplay.targets.Target

object TodoListItem {
    val COMPLETE_ITEM: Target = Target.the("the complete item tick box")
            .locatedBy("//*[@class='view' and contains(.,'{0}')]//input[@type='checkbox']")

}