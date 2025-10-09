package com.dergruenkohl.hypixelapi.controllers

import org.apache.logging.log4j.LogManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    private val logger = LogManager.getLogger()


    @GetMapping("/test/{arg}")
    suspend fun test(@PathVariable arg: String): String {
        logger.info("Test endpoint called with $arg")
        return arg
    }
}