package com.dergruenkohl.hypixelapi.exceptions

import io.ktor.http.HttpStatusCode

class HypixelRequestException(message: String?, val statusCode: HttpStatusCode) : Exception(message) {
    fun get() = "HypixelRequestException(statusCode=$statusCode, message=${message ?: "No message"})"
}
class BadRequestException(message: String?) : Exception(message)