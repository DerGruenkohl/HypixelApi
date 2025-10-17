package com.dergruenkohl.hypixelapi.client.data

data class BestiaryData(
    val kills: Map<String, Long> = emptyMap(),
    val deaths: Map<String, Long> = emptyMap(),
    val milestones: Map<String, Long> = emptyMap()
)
