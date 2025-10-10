package com.dergruenkohl.hypixelapi.client

import com.fasterxml.jackson.databind.DeserializationFeature
import com.github.benmanes.caffeine.cache.Cache
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.header
import io.ktor.client.statement.HttpReceivePipeline
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import io.ktor.serialization.jackson.jackson
import io.ktor.util.AttributeKey
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HttpClient(private val httpCache: Cache<String, String>) {


    @Bean
    fun getHttpClient(): HttpClient = HttpClient(CIO) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
            sanitizeHeader { header -> header == HttpHeaders.Authorization }
            sanitizeHeader { header -> header == "API-Key" }
        }

        install(ContentNegotiation) {
            jackson {
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            }
        }

        defaultRequest {
            header("X-User-Agent", "HypixelApi-Wrapper")
        }

        // Intercept request to check cache

    }
}