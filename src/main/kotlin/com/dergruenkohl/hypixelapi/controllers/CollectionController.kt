package com.dergruenkohl.hypixelapi.controllers

import com.dergruenkohl.hypixelapi.client.data.collections.CollectionItems
import com.dergruenkohl.hypixelapi.data.Collections
import com.dergruenkohl.hypixelapi.mapper.CollectionMapper
import com.dergruenkohl.hypixelapi.services.CollectionService
import com.dergruenkohl.hypixelapi.services.UUIDService
import org.apache.logging.log4j.LogManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class CollectionController(
    private val collectionService: CollectionService,
    private val collectionMapper: CollectionMapper,
    private val uuidService: UUIDService
) {
    private val log = LogManager.getLogger()
    @GetMapping("/collections/{ign}")
    suspend fun getPlayerCollections(@PathVariable ign: String,
                          @RequestHeader("API-Key") apiKey: String,
                          @RequestHeader("User-Agent") userAgent: String
    ): Collections {
        log.info("Getting collections for $ign")
        return collectionMapper.mapTo(collectionService.getPlayerCollections(uuidService.getUUID(ign), apiKey, userAgent))
    }
    @GetMapping("/collections/{ign}/{type}")
    suspend fun getPlayerCollectionsByType(
                            @PathVariable ign: String,
                            @PathVariable type: String,
                            @RequestHeader("API-Key") apiKey: String,
                            @RequestHeader("User-Agent") userAgent: String
    ): Collections {
        log.info("Getting $type collections for $ign")
        return collectionMapper
            .mapTo(collectionService.getPlayerCollections(uuidService.getUUID(ign), apiKey, userAgent))
            .filter { it.value.collectionType == type.uppercase() }
    }
}