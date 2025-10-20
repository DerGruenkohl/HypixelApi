package com.dergruenkohl.hypixelapi.services.impl

import com.dergruenkohl.hypixelapi.client.config.ApiConfiguration
import com.dergruenkohl.hypixelapi.client.data.HypixelPlayer
import com.dergruenkohl.hypixelapi.client.data.HypixelPlayerReply
import com.dergruenkohl.hypixelapi.exceptions.HypixelRequestException
import com.dergruenkohl.hypixelapi.services.PlayerRequestService
import com.github.benmanes.caffeine.cache.Cache
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.userAgent
import org.springframework.stereotype.Service

@Service
class PlayerRequestServiceImpl(private val client: HttpClient, private val cache: Cache<String, HypixelPlayerReply>, private val apiConfig: ApiConfiguration): PlayerRequestService {
    override suspend fun getPlayerData(uuid: String): HypixelPlayer {
        val url = "https://api.hypixel.net/player?uuid=$uuid"
        val player = cache.getIfPresent(url) ?: run {
            val response  = client.get(url){
                header("API-Key", apiConfig.apiKey)
            }
            val profileResponse = response.body<HypixelPlayerReply>()
            if(!profileResponse.success){
                throw HypixelRequestException(profileResponse.cause, response.status)
            }
            cache.put(url, profileResponse)
            profileResponse
        }
        return player.player
    }
}