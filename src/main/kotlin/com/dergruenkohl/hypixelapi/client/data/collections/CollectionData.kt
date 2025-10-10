package com.dergruenkohl.hypixelapi.client.data.collections

import io.swagger.v3.oas.annotations.media.Schema

@Schema(hidden = true)
data class CollectionData(
    val success: Boolean,
    val lastUpdated: Long,
    val version: String,
    val collections: Map<String, CollectionTypes>
)
@Schema(hidden = true)
data class CollectionTypes(
    val name: String,
    val items: Map<String, CollectionItems>

)
@Schema(hidden = true)
data class CollectionItems(
    val name: String,
    val maxTiers: Int,
    val tiers: List<CollectionTier>
)
@Schema(hidden = true)
data class CollectionTier(
    val tier: Int,
    val amountRequired: Int
)