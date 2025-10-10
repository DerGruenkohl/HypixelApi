package com.dergruenkohl.hypixelapi.services.impl

import com.dergruenkohl.hypixelapi.client.data.SkyblockProfile
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfileMember
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfiles
import com.dergruenkohl.hypixelapi.services.SkyblockService
import com.github.benmanes.caffeine.cache.Cache
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.userAgent
import org.springframework.stereotype.Service

@Service
class SkyblockServiceImpl(
    private val client: HttpClient,
    private val profilesCache: Cache<String, SkyblockProfiles>,
    private val profileCache: Cache<String, SkyblockProfile>
): SkyblockService {
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

    override suspend fun getSelectedProfileMember(
        uuid: String,
        apiKey: String,
        userAgent: String
    ): SkyblockProfileMember {
        return getSelectedProfile(uuid, apiKey, userAgent).members.getValue(uuid)
    }

    override suspend fun getProfiles(
        uuid: String,
        apiKey: String,
        userAgent: String
    ): List<SkyblockProfile> {
        val url = "https://api.hypixel.net/v2/skyblock/profiles?uuid=$uuid"
        val profiles = profilesCache.getIfPresent(url) ?: run {
            val profileResponse  = client.get(url){
                header("API-key", apiKey)
                userAgent(userAgent)
            }.body<SkyblockProfiles>()
            profilesCache.put(url, profileResponse)
            profileResponse
        }
        return profiles.profiles
    }

    override suspend fun getProfile(
        profileId: String,
        apiKey: String,
        userAgent: String
    ): SkyblockProfile {
        val url = "https://api.hypixel.net/v2/skyblock/profiles?profile=$profileId"
        val profile = profileCache.getIfPresent(url) ?: run {
            val profileResponse = client.get(url){
                header("API-key", apiKey)
                userAgent(userAgent)
            }.body<SkyblockProfile>()
            profileCache.put(url, profileResponse)
            profileResponse
        }
        return profile
    }
}