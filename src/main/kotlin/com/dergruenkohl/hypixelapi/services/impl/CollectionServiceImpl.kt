package com.dergruenkohl.hypixelapi.services.impl

import com.dergruenkohl.hypixelapi.client.data.collections.CollectionData
import com.dergruenkohl.hypixelapi.services.CollectionService
import com.dergruenkohl.hypixelapi.services.SkyblockService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service

@Service
@Configuration
class CollectionServiceImpl(private val client: HttpClient, private val skyblockService: SkyblockService): CollectionService {

    private lateinit var collectionData: CollectionData

    override suspend fun retrieveCollectionData(): CollectionData {
        if(!this::collectionData.isInitialized){
            collectionData = client.get("https://api.hypixel.net/v2/resources/skyblock/collections").body()
        }
        return collectionData
    }

    override suspend fun getPlayerCollections(uuid: String, apiKey: String, userAgent: String): Map<String, Long> {
        val profile = skyblockService.getSelectedProfileMember(uuid, apiKey, userAgent)
        return profile.collection
    }

}