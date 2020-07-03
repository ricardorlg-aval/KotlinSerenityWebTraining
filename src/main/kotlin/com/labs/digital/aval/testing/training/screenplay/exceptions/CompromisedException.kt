package com.labs.digital.aval.testing.training.screenplay.exceptions

class CompromisedException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}