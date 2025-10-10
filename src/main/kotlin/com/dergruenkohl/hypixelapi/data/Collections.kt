package com.dergruenkohl.hypixelapi.data

typealias Collections = Map<String, HypixelCollection>

data class HypixelCollection(
    val name: String,
    val collectionType: String,
    val collected: Long,
    val tier: Int,
    val maxTier: Int
)