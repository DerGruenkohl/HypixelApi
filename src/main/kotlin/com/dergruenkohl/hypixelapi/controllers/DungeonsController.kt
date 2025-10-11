package com.dergruenkohl.hypixelapi.controllers

import com.dergruenkohl.hypixelapi.data.Collections
import com.dergruenkohl.hypixelapi.data.Dungeons
import com.dergruenkohl.hypixelapi.mapper.DungeonMapper
import com.dergruenkohl.hypixelapi.services.SkyblockService
import com.dergruenkohl.hypixelapi.services.UUIDService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.logging.log4j.LogManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Dungeons", description = "Get Someones Skyblock Dungeon stats")
class DungeonsController(private val sbervice: SkyblockService, private val uuidService: UUIDService) {

    private val log = LogManager.getLogger()

    @GetMapping("/v1/dungeons/{ign}")
    @Operation(summary = "Get players Dungeon stats", description = "Retrieves dungeon stats for a specific player by IGN")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved stats")
    suspend fun getPlayerCollections(
        @Parameter(description = "Ingame name") @PathVariable ign: String,
        @Parameter(hidden = false, description = "Hypixel Api Key") @RequestHeader("API-Key") apiKey: String,
        @Parameter(hidden = true) @RequestHeader("User-Agent") userAgent: String
    ): Dungeons {
        log.info("Getting dungeons for $ign")
        return DungeonMapper.map(sbervice.getSelectedProfileMember(uuidService.getUUID(ign), apiKey, userAgent).dungeons)
    }
}