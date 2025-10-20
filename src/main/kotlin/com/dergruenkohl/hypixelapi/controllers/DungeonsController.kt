package com.dergruenkohl.hypixelapi.controllers

import com.dergruenkohl.hypixelapi.client.data.PlayerClass
import com.dergruenkohl.hypixelapi.data.ClassLevel
import com.dergruenkohl.hypixelapi.data.Collections
import com.dergruenkohl.hypixelapi.data.DungeonCompletion
import com.dergruenkohl.hypixelapi.data.DungeonCompletions
import com.dergruenkohl.hypixelapi.data.Dungeons
import com.dergruenkohl.hypixelapi.data.getByName
import com.dergruenkohl.hypixelapi.exceptions.BadRequestException
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
    suspend fun getPlayerDungeons(
        @Parameter(description = "Ingame name") @PathVariable ign: String,
    ): Dungeons {
        log.info("Getting dungeons for $ign")
        return DungeonMapper.map(sbervice.getSelectedProfileMember(uuidService.getUUID(ign)).dungeons)
    }

    @GetMapping("/v1/dungeons/{ign}/floor/{floor}")
    @Operation(summary = "Get a players Dungeon floor stats")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved stats")
    suspend fun getPlayerDungeonFloor(
        @Parameter(description = "Ingame name") @PathVariable ign: String,
        @Parameter(description = "Dungeon Floor", example = "f7") @PathVariable floor: String,
    ): DungeonCompletion {
        log.info("Getting dungeon $floor stats for $ign")
        val dungeons =  DungeonMapper.map(sbervice.getSelectedProfileMember(uuidService.getUUID(ign)).dungeons)
        return dungeons.completions.getByName(floor)

    }
    @GetMapping("/v1/dungeons/{ign}/floor")
    @Operation(summary = "Get a players Dungeon floor stats")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved stats")
    suspend fun getPlayerDungeonFloors(
        @Parameter(description = "Ingame name") @PathVariable ign: String,
    ): DungeonCompletions {
        log.info("Getting dungeon floor stats for $ign")
        val dungeons =  DungeonMapper.map(sbervice.getSelectedProfileMember(uuidService.getUUID(ign)).dungeons)
        return dungeons.completions

    }
    @GetMapping("/v1/dungeons/{ign}/secrets")
    @Operation(summary = "Get a players secret counts")
    suspend fun getSecrets(
        @Parameter(description = "Ingame name") @PathVariable ign: String,
    ): Long{
        return DungeonMapper.map(sbervice.getSelectedProfileMember(uuidService.getUUID(ign)).dungeons).secrets
    }


    @GetMapping("/v1/dungeons/{ign}/class/{dungeonclass}")
    @Operation(summary = "Get a players Class level")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved stats")
    suspend fun getPlayerDungeonClass(
        @Parameter(description = "Ingame name") @PathVariable ign: String,
        @Parameter(description = "Dungeon Class", example = "healer") @PathVariable dungeonclass: String,
    ): ClassLevel {
        log.info("Getting class $dungeonclass stats for $ign")
        val dungeons =  DungeonMapper.map(sbervice.getSelectedProfileMember(uuidService.getUUID(ign)).dungeons)
        return dungeons.classLevels[dungeonclass]?: throw BadRequestException("Invalid class name")
    }
    @GetMapping("/v1/dungeons/{ign}/class")
    @Operation(summary = "Get a players Class level")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved stats")
    suspend fun getPlayerDungeonClasses(
        @Parameter(description = "Ingame name") @PathVariable ign: String,
    ): Map<String, ClassLevel> {
        log.info("Getting class stats for $ign")
        val dungeons =  DungeonMapper.map(sbervice.getSelectedProfileMember(uuidService.getUUID(ign)).dungeons)
        return dungeons.classLevels
    }
}