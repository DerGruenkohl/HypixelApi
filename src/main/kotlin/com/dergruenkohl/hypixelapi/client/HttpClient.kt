package com.dergruenkohl.hypixelapi.client


import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HttpClient() {

    @Bean
    fun getHttpClient(): HttpClient = HttpClient(CIO) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
            sanitizeHeader { header -> header == HttpHeaders.Authorization }
            sanitizeHeader { header -> header == "API-Key" }
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                encodeDefaults = true
            })
        }

        defaultRequest {
            header("X-User-Agent", "HypixelApi-Wrapper")
        }

        // Intercept request to check cache

    }
}