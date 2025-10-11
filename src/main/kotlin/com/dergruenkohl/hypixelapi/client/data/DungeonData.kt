package com.dergruenkohl.hypixelapi.client.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DungeonData(
    @SerialName("dungeon_types")
    val dungeonTypes: DungeonTypes = DungeonTypes(),
    @SerialName("player_classes")
    val playerClasses: Map<String, PlayerClass> = emptyMap(),
    val secrets: Long = 0
)
@Serializable
data class DungeonTypes(
    val catacombs: DungeonType = DungeonType(),
    @SerialName("master_catacombs")
    val masterCatacombs: DungeonType = DungeonType(),
)

@Serializable
data class PlayerClass(
    val experience: Double = 0.0,
)
@Serializable
data class DungeonType(
    @SerialName("tier_completions")
    val tierCompletions: Map<String, Double> = emptyMap(),
    @SerialName("fastest_time")
    val fastestTime: Map<String, Double> = emptyMap(),
    val experience: Double = 0.0,
    @SerialName("fastest_time_s_plus")
    val fastestTimeSPlus: Map<String, Double> = emptyMap(),
    @SerialName("fastest_time_s")
    val fastestTimeS: Map<String, Double> = emptyMap(),
)