package com.dergruenkohl.hypixelapi.services.impl

import com.dergruenkohl.hypixelapi.services.PlayerRequestService
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.userAgent
import org.springframework.stereotype.Service

@Service
class PlayerRequestServiceImpl(private val client: HttpClient): PlayerRequestService {
    override suspend fun getPlayerData(uuid: String, apiKey: String): String {
        return client.get("https://api.hypixel.net/player?uuid=$uuid"){
            header("API-key", apiKey)
            userAgent("Api-Wrapper / Original agent: dev")
        }.bodyAsText()
    }
}