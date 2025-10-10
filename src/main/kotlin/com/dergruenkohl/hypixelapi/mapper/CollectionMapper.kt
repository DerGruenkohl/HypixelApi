package com.dergruenkohl.hypixelapi.mapper

import com.dergruenkohl.hypixelapi.data.Collections
import com.dergruenkohl.hypixelapi.data.HypixelCollection
import com.dergruenkohl.hypixelapi.services.CollectionService
import org.springframework.stereotype.Component

@Component
class CollectionMapper(
    private val collectionService: CollectionService
) {

    suspend fun mapTo(collections: Map<String, Long>): Collections {
        val collectionData = collectionService.retrieveCollectionData()

        return collections.mapNotNull { (name, collectedAmount) ->
            collectionData.collections.entries.firstNotNullOfOrNull { (collectionType, typeData) ->
                typeData.items[name]?.let { itemData ->
                    val currentTier = itemData.tiers.lastOrNull { it.amountRequired <= collectedAmount }?.tier ?: 0

                    name to HypixelCollection(
                        name = itemData.name,
                        collectionType = collectionType,
                        collected = collectedAmount,
                        tier = currentTier,
                        maxTier = itemData.maxTiers
                    )
                }
            }
        }.toMap()
    }

}