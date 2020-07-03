package com.labs.digital.aval.testing.training.screenplay.exceptions

class MissingTodoItemsException : AssertionError {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}