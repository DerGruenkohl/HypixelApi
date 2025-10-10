package com.dergruenkohl.hypixelapi.services

import com.dergruenkohl.hypixelapi.client.data.collections.CollectionData

interface CollectionService {
    suspend fun retrieveCollectionData() : CollectionData
    suspend fun getPlayerCollections(uuid: String, apiKey: String, userAgent: String) : Map<String, Long>

}