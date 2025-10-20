package com.dergruenkohl.hypixelapi.services

import com.dergruenkohl.hypixelapi.client.data.SkyblockProfile
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfileMember
import com.dergruenkohl.hypixelapi.data.Skills


/**
 * Service to get generic information about a Skyblock profile
 */
interface SkyblockService {
    suspend fun getSelectedProfileId(uuid: String): String
    suspend fun getSelectedProfile(uuid: String): SkyblockProfile
    suspend fun getSelectedProfileMember(uuid: String): SkyblockProfileMember
    suspend fun getProfiles(uuid: String): List<SkyblockProfile>
    suspend fun getProfile(profileId: String): SkyblockProfile
    suspend fun getSkills(uuid: String): Skills
}