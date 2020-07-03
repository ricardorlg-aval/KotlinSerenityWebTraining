package com.labs.digital.aval.testing.training.screenplay.questions

import com.labs.digital.aval.testing.training.screenplay.ui.TodoList
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Question
import net.serenitybdd.screenplay.questions.Text

class TheTodoItems:Question<List<String>> {
    override fun answeredBy(actor: Actor): List<String> {
        return Text.of(TodoList.ITEMS)
                .viewedBy(actor)
                .asList()
    }
    companion object{
        fun displayed()=TheTodoItems()
    }
}