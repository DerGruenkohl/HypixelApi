package com.dergruenkohl.hypixelapi

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "hypixel")
class TestConfiguration {
    lateinit var apiKey: String
}

