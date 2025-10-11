package com.dergruenkohl.hypixelapi.data

import kotlinx.serialization.Serializable
import kotlin.div
import kotlin.text.toInt

@Serializable
data class Dungeons(
    val level: DungeonLevel,
    val completions: DungeonCompletions,
)

@Serializable
data class DungeonLevel(
    val exp: Long,
    val level: Int,
    val overflowLevel: Int,
) {
    companion object {
        val levelThresholds = listOf(
            50, 125, 235, 395, 625, 955, 1425, 2095, 3045, 4385,
            6275, 8940, 12700, 17960, 25340, 35640, 50040, 70040, 97640, 135640,
            188140, 259640, 356640, 488640, 668640, 911640, 1239640, 1684640, 2284640, 3084640,
            4149640, 5559640, 7459640, 9959640, 13259640, 17559640, 23159640, 30359640, 39559640, 51559640,
            66559640, 85559640, 109559640, 139559640, 177559640, 225559640, 285559640, 360559640, 453559640, 569809640
        )

        fun fromExp(exp: Double): DungeonLevel {
            for ((level, threshold) in levelThresholds.withIndex()) {
                if (exp < threshold) {
                    return DungeonLevel(exp.toLong(), level, level)
                }
            }
            // Overflow calculation
            val maxLevel = levelThresholds.size
            val overflowExp = exp - levelThresholds.last()
            val overflowLevel = (overflowExp / 200_000_000).toInt()
            return DungeonLevel(exp.toLong(), maxLevel, maxLevel + overflowLevel)
        }

    }
}

@Serializable
data class DungeonCompletions(
    val entrance: DungeonCompletion,
    val floor1: DungeonCompletion,
    val floor2: DungeonCompletion,
    val floor3: DungeonCompletion,
    val floor4: DungeonCompletion,
    val floor5: DungeonCompletion,
    val floor6: DungeonCompletion,
    val floor7: DungeonCompletion,
    val masterMode1: DungeonCompletion,
    val masterMode2: DungeonCompletion,
    val masterMode3: DungeonCompletion,
    val masterMode4: DungeonCompletion,
    val masterMode5: DungeonCompletion,
    val masterMode6: DungeonCompletion,
    val masterMode7: DungeonCompletion,
)

@Serializable
data class DungeonCompletion(
    val bestRunTime: DungeonRuntime,
    val bestRunTimeS: DungeonRuntime,
    val bestRunTimeSPlus: DungeonRuntime,
    val timesBeaten: Int,
)
@Serializable
data class DungeonRuntime(
    val ms: Int,
    val duration: String
){
    companion object {
        fun fromSeconds(ms: Int) = DungeonRuntime(ms, "${ms/1000 / 60}m:${ms/1000 % 60}s")
    }
}