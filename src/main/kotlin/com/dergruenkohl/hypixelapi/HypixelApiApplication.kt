package com.dergruenkohl.hypixelapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HypixelApiApplication

fun main(args: Array<String>) {
    runApplication<HypixelApiApplication>(*args)
}
