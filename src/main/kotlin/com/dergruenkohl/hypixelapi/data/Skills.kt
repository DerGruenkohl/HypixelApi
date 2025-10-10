package com.dergruenkohl.hypixelapi.data

import kotlinx.serialization.Serializable


@Serializable
data class Skill(
    val experience: Double,
    val level: Int,
){
    companion object{
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
        fun fromExp(exp: Double): Skill{
            // Find the corresponding level
            for ((level, threshold) in levelThresholds.withIndex()) {
                if (exp < threshold) {
                    return Skill(exp, level)
                }
            }
            // If the XP is greater than the highest threshold, return the max level
            return Skill(exp, levelThresholds.size - 1)
        }
    }
}

@Serializable
data class Skills(
    val fishing: Skill,
    val alchemy: Skill,
    val runecrafting: Skill,
    val mining: Skill,
    val farming: Skill,
    val enchanting: Skill,
    val taming: Skill,
    val foraging: Skill,
    val social: Skill,
    val carpentry: Skill,
    val combat: Skill,
)
