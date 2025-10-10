package com.dergruenkohl.hypixelapi.controllers

import com.dergruenkohl.hypixelapi.client.data.SkyblockProfile
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfileMember
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfiles
import com.dergruenkohl.hypixelapi.data.Skills
import com.dergruenkohl.hypixelapi.services.PlayerRequestService
import com.dergruenkohl.hypixelapi.services.SkyblockService
import com.dergruenkohl.hypixelapi.services.UUIDService
import org.apache.logging.log4j.LogManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class SkyblockProfileController(
    private val uuidService: UUIDService,
    private val skyblockService: SkyblockService,
) {
    private val log = LogManager.getLogger()
    @GetMapping("/profiles/{ign}")
    suspend fun getPlayer(@PathVariable ign: String,
                          @RequestHeader("API-Key") apiKey: String,
                          @RequestHeader("User-Agent") userAgent: String
    ): List<SkyblockProfile> {
        log.info("getting profiles for: $ign")
        return skyblockService.getProfiles(uuidService.getUUID(ign), apiKey, userAgent)
    }
    @GetMapping("/profiles/{ign}/selected")
    suspend fun getSelectedProfile(@PathVariable ign: String,
                          @RequestHeader("API-Key") apiKey: String,
                          @RequestHeader("User-Agent") userAgent: String
    ): SkyblockProfile {
        log.info("getting selected profile for: $ign")
        return skyblockService.getSelectedProfile(uuidService.getUUID(ign), apiKey, userAgent)
    }
    @GetMapping("/profiles/{ign}/selected/member")
    suspend fun getSelectedProfileMember(@PathVariable ign: String,
                          @RequestHeader("API-Key") apiKey: String,
                          @RequestHeader("User-Agent") userAgent: String
    ): SkyblockProfileMember {
        log.info("getting selected profile member for: $ign")
        return skyblockService.getSelectedProfileMember(uuidService.getUUID(ign), apiKey, userAgent)
    }
    @GetMapping("/skills/{ign}")
    suspend fun getSkills(@PathVariable ign: String,
                                         @RequestHeader("API-Key") apiKey: String,
                                         @RequestHeader("User-Agent") userAgent: String
    ): Skills {
        log.info("getting skills for: $ign")
        return skyblockService.getSkills(uuidService.getUUID(ign), apiKey, userAgent)
    }
}