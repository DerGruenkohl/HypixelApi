package com.dergruenkohl.hypixelapi.controllers

import com.dergruenkohl.hypixelapi.data.Collections
import com.dergruenkohl.hypixelapi.mapper.CollectionMapper
import com.dergruenkohl.hypixelapi.services.CollectionService
import com.dergruenkohl.hypixelapi.services.UUIDService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.logging.log4j.LogManager
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "Collections", description = "Get Someones Skyblock Collections")
class CollectionController(
    private val collectionService: CollectionService,
    private val collectionMapper: CollectionMapper,
    private val uuidService: UUIDService
) {
    private val log = LogManager.getLogger()

    @GetMapping("/collections/{ign}")
    @Operation(summary = "Get player collections", description = "Retrieves all collections for a specific player by IGN")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved collections")
    suspend fun getPlayerCollections(
        @Parameter(description = "Ingame name") @PathVariable ign: String,
        @Parameter(hidden = false, description = "Hypixel Api Key") @RequestHeader("API-Key") apiKey: String,
        @Parameter(hidden = true) @RequestHeader("User-Agent") userAgent: String
    ): Collections {
        log.info("Getting collections for $ign")
        return collectionMapper.mapTo(collectionService.getPlayerCollections(uuidService.getUUID(ign), apiKey, userAgent))
    }

    @GetMapping("/collections/{ign}/{type}")
    @Operation(summary = "Get player collections by type")
    suspend fun getPlayerCollectionsByType(
        @Parameter(description = "Player in-game name") @PathVariable ign: String,
        @Parameter(description = "Collection type filter", example = "farming") @PathVariable type: String,
        @Parameter(hidden = false) @RequestHeader("API-Key") apiKey: String,
        @Parameter(hidden = true) @RequestHeader("User-Agent") userAgent: String
    ): Collections {
        log.info("Getting $type collections for $ign")
        return collectionMapper
            .mapTo(collectionService.getPlayerCollections(uuidService.getUUID(ign), apiKey, userAgent))
            .filter { it.value.collectionType == type.uppercase() }
    }
}
