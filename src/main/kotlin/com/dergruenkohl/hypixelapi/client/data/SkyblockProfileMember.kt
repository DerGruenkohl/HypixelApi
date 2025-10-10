package com.dergruenkohl.hypixelapi.client.data

import com.fasterxml.jackson.annotation.JsonProperty

data class SkyblockProfileMember(
    @JsonProperty("player_id")
    val uuid: String,
    @JsonProperty("player_data")
    val playerData: SkyblockProfileMemberData,
    val collection: Map<String, Long> = emptyMap()
)
data class SkyblockProfileMemberData(
    @JsonProperty("last_death")
    val lastDeath: Long = -1L,
    @JsonProperty("death_count")
    val deathCount: Int = -1,
    @JsonProperty("experience")
    val skills: SkyblockProfileMemberSkills = SkyblockProfileMemberSkills()

)
data class SkyblockProfileMemberSkills(
    @JsonProperty("SKILL_FISHING")
    val fishing: Double = 0.0,
    @JsonProperty("SKILL_ALCHEMY")
    val alchemy: Double= 0.0,
    @JsonProperty("SKILL_RUNECRAFTING")
    val runecrafting: Double = 0.0,
    @JsonProperty("SKILL_MINING")
    val mining: Double = 0.0,
    @JsonProperty("SKILL_FARMING")
    val farming: Double= 0.0,
    @JsonProperty("SKILL_ENCHANTING")
    val enchanting: Double = 0.0,
    @JsonProperty("SKILL_TAMING")
    val taming: Double = 0.0,
    @JsonProperty("SKILL_FORAGING")
    val foraging: Double = 0.0,
    @JsonProperty("SKILL_SOCIAL")
    val social: Double = 0.0,
    @JsonProperty("SKILL_CARPENTRY")
    val carpentry: Double= 0.0,
    @JsonProperty("SKILL_COMBAT")
    val combat: Double= 0.0,
)
