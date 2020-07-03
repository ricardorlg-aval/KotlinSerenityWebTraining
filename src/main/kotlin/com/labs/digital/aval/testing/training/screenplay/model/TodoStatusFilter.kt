package com.labs.digital.aval.testing.training.screenplay.model

import com.labs.digital.aval.testing.training.screenplay.exceptions.CompromisedException

enum class TodoStatusFilter {
    ALL, ACTIVE, COMPLETED;

    override fun toString(): String {
        return this.name.toLowerCase().capitalize()
    }

    fun statusName() = this.name.toLowerCase().capitalize()

    companion object {
        fun statusFilterFromName(name: String) = values().firstOrNull { it.name.equals(name, true) }
                ?: throw CompromisedException("The status filter called $name is invalid")
    }
}