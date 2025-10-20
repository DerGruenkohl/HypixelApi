package com.dergruenkohl.hypixelapi.client.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "hypixel")
class ApiConfiguration {
    lateinit var apiKey: String
}