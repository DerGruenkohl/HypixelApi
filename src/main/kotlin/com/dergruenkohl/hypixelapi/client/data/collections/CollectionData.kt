package com.dergruenkohl.hypixelapi.client.data.collections

data class CollectionData(
    val success: Boolean,
    val lastUpdated: Long,
    val version: String,
    val collections: Map<String, CollectionTypes>
)
data class CollectionTypes(
    val name: String,
    val items: Map<String, CollectionItems>

)
data class CollectionItems(
    val name: String,
    val maxTiers: Int,
    val tiers: List<CollectionTier>
)
data class CollectionTier(
    val tier: Int,
    val amountRequired: Int
)