package com.dergruenkohl.hypixelapi.client.data

import io.swagger.v3.oas.annotations.media.Schema
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Schema(hidden = true)
@Serializable
data class SkyblockProfileMember(
    @SerialName("player_id")
    val uuid: String,
    @SerialName("player_data")
    val playerData: SkyblockProfileMemberData,
    val collection: Map<String, Long> = emptyMap()
)
@Schema(hidden = true)
@Serializable
data class SkyblockProfileMemberData(
    @SerialName("last_death")
    val lastDeath: Long = -1L,
    @SerialName("death_count")
    val deathCount: Int = -1,
    @SerialName("experience")
    val skills: SkyblockProfileMemberSkills = SkyblockProfileMemberSkills()

)
@Schema(hidden = true)
@Serializable
data class SkyblockProfileMemberSkills(
    @SerialName("SKILL_FISHING")
    val fishing: Double = 0.0,
    @SerialName("SKILL_ALCHEMY")
    val alchemy: Double= 0.0,
    @SerialName("SKILL_RUNECRAFTING")
    val runecrafting: Double = 0.0,
    @SerialName("SKILL_MINING")
    val mining: Double = 0.0,
    @SerialName("SKILL_FARMING")
    val farming: Double= 0.0,
    @SerialName("SKILL_ENCHANTING")
    val enchanting: Double = 0.0,
    @SerialName("SKILL_TAMING")
    val taming: Double = 0.0,
    @SerialName("SKILL_FORAGING")
    val foraging: Double = 0.0,
    @SerialName("SKILL_SOCIAL")
    val social: Double = 0.0,
    @SerialName("SKILL_CARPENTRY")
    val carpentry: Double= 0.0,
    @SerialName("SKILL_COMBAT")
    val combat: Double= 0.0,
)
