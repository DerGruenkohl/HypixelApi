package com.dergruenkohl.hypixelapi.services

import com.dergruenkohl.hypixelapi.client.data.SkyblockProfile


/**
 * Service to get generic information about a Skyblock profile
 */
interface SkyblockService {
    suspend fun getSelectedProfileId(uuid: String, apiKey: String, userAgent: String): String
    suspend fun getSelectedProfile(uuid: String, apiKey: String, userAgent: String): SkyblockProfile
    suspend fun getProfiles(uuid: String, apiKey: String, userAgent: String): List<SkyblockProfile>
    suspend fun getProfile(profileId: String, apiKey: String, userAgent: String): SkyblockProfile
}