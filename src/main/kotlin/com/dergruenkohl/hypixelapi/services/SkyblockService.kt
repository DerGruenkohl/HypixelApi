package com.dergruenkohl.hypixelapi.services

import com.dergruenkohl.hypixelapi.client.data.SkyblockProfile
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfileMember
import com.dergruenkohl.hypixelapi.data.Skills


/**
 * Service to get generic information about a Skyblock profile
 */
interface SkyblockService {
    suspend fun getSelectedProfileId(uuid: String, apiKey: String, userAgent: String): String
    suspend fun getSelectedProfile(uuid: String, apiKey: String, userAgent: String): SkyblockProfile
    suspend fun getSelectedProfileMember(uuid: String, apiKey: String, userAgent: String): SkyblockProfileMember
    suspend fun getProfiles(uuid: String, apiKey: String, userAgent: String): List<SkyblockProfile>
    suspend fun getProfile(profileId: String, apiKey: String, userAgent: String): SkyblockProfile
    suspend fun getSkills(uuid: String, apiKey: String, userAgent: String): Skills
}