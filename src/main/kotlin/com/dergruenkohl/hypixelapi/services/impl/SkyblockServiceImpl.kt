package com.dergruenkohl.hypixelapi.services.impl

import com.dergruenkohl.hypixelapi.client.data.SkyblockProfile
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfiles
import com.dergruenkohl.hypixelapi.services.SkyblockService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.userAgent
import org.springframework.stereotype.Service

@Service
class SkyblockServiceImpl(private val client: HttpClient): SkyblockService {
    override suspend fun getSelectedProfileId(
        uuid: String,
        apiKey: String,
        userAgent: String
    ): String {
        val profiles = getProfiles(uuid, apiKey, userAgent)
        return profiles.first { it.selected }.profileId
    }

    override suspend fun getSelectedProfile(
        uuid: String,
        apiKey: String,
        userAgent: String
    ): SkyblockProfile {
        val profiles = getProfiles(uuid, apiKey, userAgent)
        return profiles.first { it.selected }
    }

    override suspend fun getProfiles(
        uuid: String,
        apiKey: String,
        userAgent: String
    ): List<SkyblockProfile> {
        return client.get("https://api.hypixel.net/v2/skyblock/profiles?uuid=$uuid"){
            header("API-key", apiKey)
            userAgent(userAgent)
        }.body<SkyblockProfiles>().profiles
    }

    override suspend fun getProfile(
        profileId: String,
        apiKey: String,
        userAgent: String
    ): SkyblockProfile {
        return client.get("https://api.hypixel.net/v2/skyblock/profile?profile=$profileId"){
            header("API-key", apiKey)
            userAgent(userAgent)
        }.body()
    }
}