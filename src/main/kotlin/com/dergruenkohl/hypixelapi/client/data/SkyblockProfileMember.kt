package com.dergruenkohl.hypixelapi.client.data

import com.fasterxml.jackson.annotation.JsonProperty

data class SkyblockProfileMember(
    @JsonProperty("player_id")
    val uuid: String,
    @JsonProperty("player_data")
    val playerData: SkyblockProfileMemberData
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
){
    companion object{
        fun toLevel(skill: Double): Int{
            val levelThresholds = listOf(
                0L, 50L, 175L, 375L, 675L, 1175L, 1925L, 2925L, 4425L, 6425L,
                9925L, 14925L, 22425L, 32425L, 47425L, 67425L, 97425L, 147425L,
                222425L, 322425L, 522425L, 822425L, 1222425L, 1722425L, 2322425L,
                3022425L, 3822425L, 4722425L, 5722425L, 6822425L, 8022425L, 9322425L,
                10722425L, 12222425L, 13822425L, 15522425L, 17322425L, 19222425L,
                21222425L, 23322425L, 25522425L, 27822425L, 30222425L, 32722425L,
                35322425L, 38072425L, 40972425L, 44072425L, 47472425L, 51172425L,
                55172425L, 59472425L, 64072425L, 68972425L, 74172425L, 79672425L,
                85472425L, 91572425L, 97972425L, 104672425L, 111672425L
            )

            // Find the corresponding level
            for ((level, threshold) in levelThresholds.withIndex()) {
                if (skill < threshold) {
                    return level - 1
                }
            }
            // If the XP is greater than the highest threshold, return the max level
            return levelThresholds.size - 1
        }
    }


}
