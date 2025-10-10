package com.dergruenkohl.hypixelapi.controllers

import com.dergruenkohl.hypixelapi.client.data.SkyblockProfile
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfileMember
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfiles
import com.dergruenkohl.hypixelapi.data.Skills
import com.dergruenkohl.hypixelapi.services.PlayerRequestService
import com.dergruenkohl.hypixelapi.services.SkyblockService
import com.dergruenkohl.hypixelapi.services.UUIDService
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.logging.log4j.LogManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Skyblock", description = "Get information about a player's skyblock profile")
class SkyblockProfileController(
    private val uuidService: UUIDService,
    private val skyblockService: SkyblockService,
) {
    private val log = LogManager.getLogger()
    @GetMapping("/profiles/{ign}")
    suspend fun getPlayer(
                          @Parameter(description = "Ingame name") @PathVariable ign: String,
                          @Parameter(hidden = false, description = "Hypixel Api Key") @RequestHeader("API-Key") apiKey: String,
                          @Parameter(hidden = true) @RequestHeader("User-Agent") userAgent: String
    ): List<SkyblockProfile> {
        log.info("getting profiles for: $ign")
        return skyblockService.getProfiles(uuidService.getUUID(ign), apiKey, userAgent)
    }
    @GetMapping("/profiles/{ign}/selected")
    suspend fun getSelectedProfile(
                                   @Parameter(description = "Ingame name") @PathVariable ign: String,
                                   @Parameter(hidden = false, description = "Hypixel Api Key") @RequestHeader("API-Key") apiKey: String,
                                   @Parameter(hidden = true) @RequestHeader("User-Agent") userAgent: String
    ): SkyblockProfile {
        log.info("getting selected profile for: $ign")
        return skyblockService.getSelectedProfile(uuidService.getUUID(ign), apiKey, userAgent)
    }
    @GetMapping("/profiles/{ign}/selected/member")
    suspend fun getSelectedProfileMember(
                                         @Parameter(description = "Ingame name") @PathVariable ign: String,
                                         @Parameter(hidden = false, description = "Hypixel Api Key") @RequestHeader("API-Key") apiKey: String,
                                         @Parameter(hidden = true) @RequestHeader("User-Agent") userAgent: String
    ): SkyblockProfileMember {
        log.info("getting selected profile member for: $ign")
        return skyblockService.getSelectedProfileMember(uuidService.getUUID(ign), apiKey, userAgent)
    }
    @GetMapping("/skills/{ign}")
    suspend fun getSkills(
                          @Parameter(description = "Ingame name") @PathVariable ign: String,
                          @Parameter(hidden = false, description = "Hypixel Api Key") @RequestHeader("API-Key") apiKey: String,
                          @Parameter(hidden = true) @RequestHeader("User-Agent") userAgent: String
    ): Skills {
        log.info("getting skills for: $ign")
        return skyblockService.getSkills(uuidService.getUUID(ign), apiKey, userAgent)
    }
}