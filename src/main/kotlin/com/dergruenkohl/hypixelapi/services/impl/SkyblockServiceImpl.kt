package com.dergruenkohl.hypixelapi.services.impl

import com.dergruenkohl.hypixelapi.client.config.ApiConfiguration
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfile
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfileMember
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfiles
import com.dergruenkohl.hypixelapi.data.Skills
import com.dergruenkohl.hypixelapi.exceptions.HypixelRequestException
import com.dergruenkohl.hypixelapi.mapper.SkillMapper
import com.dergruenkohl.hypixelapi.services.SkyblockService
import com.github.benmanes.caffeine.cache.Cache
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.userAgent
import org.springframework.stereotype.Service

@Service
class SkyblockServiceImpl(
    private val client: HttpClient,
    private val profilesCache: Cache<String, SkyblockProfiles>,
    private val profileCache: Cache<String, SkyblockProfile>,
    private val apiConfig: ApiConfiguration
): SkyblockService {
    override suspend fun getSelectedProfileId(
        uuid: String,
    ): String {
        val profiles = getProfiles(uuid)
        return profiles.first { it.selected }.profileId
    }

    override suspend fun getSelectedProfile(
        uuid: String,
    ): SkyblockProfile {
        val profiles = getProfiles(uuid)
        return profiles.first { it.selected }
    }

    override suspend fun getSelectedProfileMember(
        uuid: String,
    ): SkyblockProfileMember {
        return getSelectedProfile(uuid).members.getValue(uuid)
    }

    override suspend fun getProfiles(
        uuid: String,
    ): List<SkyblockProfile> {
        val url = "https://api.hypixel.net/v2/skyblock/profiles?uuid=$uuid"
        val profiles = profilesCache.getIfPresent(url) ?: run {
            val response  = client.get(url){
                header("API-Key", apiConfig.apiKey)
            }
            val profileResponse = response.body<SkyblockProfiles>()
            if(!profileResponse.success){
                throw HypixelRequestException(profileResponse.cause, response.status)
            }
            profilesCache.put(url, profileResponse)
            profileResponse
        }
        return profiles.profiles
    }

    override suspend fun getProfile(
        profileId: String,
    ): SkyblockProfile {
        val url = "https://api.hypixel.net/v2/skyblock/profiles?profile=$profileId"
        val profile = profileCache.getIfPresent(url) ?: run {
            val response = client.get(url){
                header("API-Key", apiConfig.apiKey)
            }
            val profileResponse = response.body<SkyblockProfile>()
            if(!profileResponse.success){
                throw HypixelRequestException(profileResponse.cause, response.status)
            }
            profileCache.put(url, profileResponse)
            profileResponse
        }
        return profile
    }

    override suspend fun getSkills(
        uuid: String,
    ): Skills {
        val selectedProfile = getSelectedProfileMember(uuid)
        val profileSkills = selectedProfile.playerData.skills
        return SkillMapper.fromSkyblockProfileMemberSkills(profileSkills)
    }
}