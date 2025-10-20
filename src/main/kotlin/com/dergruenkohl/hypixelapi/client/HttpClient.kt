package com.dergruenkohl.hypixelapi.client


import com.dergruenkohl.hypixelapi.client.config.ApiConfiguration
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*
import io.ktor.http.HttpHeaders
import io.ktor.http.userAgent
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
            level = LogLevel.HEADERS
            //sanitizeHeader { header -> header == HttpHeaders.Authorization }
           // sanitizeHeader { header -> header == "API-Key" }
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                encodeDefaults = true
            })
        }

        defaultRequest {
            userAgent("WheatHypixelApi")
        }
    }
}