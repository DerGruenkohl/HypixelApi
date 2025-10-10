package com.dergruenkohl.hypixelapi.data

import kotlinx.serialization.Serializable

typealias Collections = Map<String, HypixelCollection>

@Serializable
data class HypixelCollection(
    val name: String,
    val collectionType: String,
    val collected: Long,
    val tier: Int,
    val maxTier: Int
)