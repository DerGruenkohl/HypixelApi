package com.dergruenkohl.hypixelapi.controllers

import com.dergruenkohl.hypixelapi.client.data.HypixelPlayer
import com.dergruenkohl.hypixelapi.client.data.HypixelPlayerReply
import com.dergruenkohl.hypixelapi.services.PlayerRequestService
import com.dergruenkohl.hypixelapi.services.UUIDService
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.logging.log4j.LogManager

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Player", description = "Get general information about a player on hypixel")
class PlayerRequestController(
    val playerRequestService: PlayerRequestService,
    val uuidService: UUIDService
) {
    private val log = LogManager.getLogger()
    @GetMapping("/v1/player/{ign}")
    suspend fun getPlayer(
                          @Parameter(description = "Ingame name") @PathVariable ign: String,
                          @Parameter(hidden = false, description = "Hypixel Api Key") @RequestHeader("API-Key") apiKey: String,
                          @Parameter(hidden = true) @RequestHeader("User-Agent") userAgent: String
    ): HypixelPlayer {
        log.info("Player endpoint called with $ign")
        return playerRequestService.getPlayerData(uuidService.getUUID(ign), apiKey, userAgent)
    }
    @GetMapping("/v1/player/{ign}/discord")
    @Tag(name = "Discord", description = "Get the linked discord of a player")
    suspend fun getPlayerDiscord(
        @Parameter(description = "Ingame name") @PathVariable ign: String,
        @Parameter(hidden = false, description = "Hypixel Api Key") @RequestHeader("API-Key") apiKey: String,
        @Parameter(hidden = true) @RequestHeader("User-Agent") userAgent: String
    ): String{
        log.info("Getting discord for $ign")
        return playerRequestService.getPlayerData(uuidService.getUUID(ign), apiKey, userAgent).socialMedia.links.discord

    }
}