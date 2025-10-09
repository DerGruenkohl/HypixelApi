package com.dergruenkohl.hypixelapi.controllers

import com.dergruenkohl.hypixelapi.services.PlayerRequestService
import com.dergruenkohl.hypixelapi.services.UUIDService
import org.apache.logging.log4j.LogManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class PlayerRequestController(
    val playerRequestService: PlayerRequestService,
    val uuidService: UUIDService
) {
    private val log = LogManager.getLogger()
    @GetMapping("/player/{ign}")
    suspend fun getPlayer(@PathVariable ign: String,
                          @RequestHeader("API-Key") apiKey: String,
                          @RequestHeader("User-Agent") userAgent: String
    ): String {
        log.info("Player endpoint called with $ign")
        return playerRequestService.getPlayerData(uuidService.getUUID(ign), apiKey, userAgent)
    }
}