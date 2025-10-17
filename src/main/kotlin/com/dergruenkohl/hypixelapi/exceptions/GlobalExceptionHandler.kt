package com.dergruenkohl.hypixelapi.exceptions

import io.ktor.serialization.JsonConvertException
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LogManager.getLogger()

    @ExceptionHandler(HypixelRequestException::class)
    fun handleHypixelRequestException(ex: HypixelRequestException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.valueOf(ex.statusCode.value))
    }
    @ExceptionHandler(JsonConvertException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleJsonConvertException(ex: JsonConvertException): String {
        logger.error("Could not parse response from the Hypixel API", ex)
        return "Could not parse response from the Hypixel API Please report this to the developer."
    }
    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequestException(ex: BadRequestException): String{
        logger.warn(ex)
        return ex.message ?: "Bad Request"
    }
}