
package com.dergruenkohl.hypixelapi.controllers

import com.dergruenkohl.hypixelapi.client.config.ApiConfiguration
import com.dergruenkohl.hypixelapi.client.HttpClient
import com.dergruenkohl.hypixelapi.client.config.CacheConfig
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfile
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfileMember
import com.dergruenkohl.hypixelapi.data.Skills
import com.dergruenkohl.hypixelapi.services.impl.SkyblockServiceImpl
import com.dergruenkohl.hypixelapi.services.impl.UUIDServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(controllers = [SkyblockProfileController::class])
@ExtendWith(SpringExtension::class)
@Import(SkyblockServiceImpl::class, UUIDServiceImpl::class, HttpClient::class, CacheConfig::class, ApiConfiguration::class)
class SkyblockProfileControllerTest @Autowired constructor(
    webClient: WebTestClient,
    testConfig: ApiConfiguration,
) {
    val webClient =  webClient.mutate()
        .responseTimeout(java.time.Duration.ofSeconds(10)).build()
    val apiKey = testConfig.apiKey
    init {

    }

    @Test
    fun testProfilesEndpointReturnsProfiles() {
        webClient.get()
            .uri("/v1/profiles/garaf")
            .header("API-Key", apiKey)
            .header("User-Agent", "HypixelApi-WrapperTest")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(SkyblockProfile::class.java)
    }
    @Test
    fun testProfileEndpointReturnsProfile() {
        webClient.get()
            .uri("/v1/profiles/garaf/selected")
            .header("API-Key", apiKey)
            .header("User-Agent", "HypixelApi-WrapperTest")
            .exchange()
            .expectStatus().isOk
            .expectBody(SkyblockProfile::class.java)
    }
    @Test
    fun testProfileMemberEndpointReturnsProfileMember() {
        webClient.get()
            .uri("/v1/profiles/garaf/selected/member")
            .header("API-Key", apiKey)
            .header("User-Agent", "HypixelApi-WrapperTest")
            .exchange()
            .expectStatus().isOk
            .expectBody(SkyblockProfileMember::class.java)
    }
    @Test
    fun testSkillEndpointReturnsSkill() {
        webClient.get()
            .uri("/v1/skills/garaf")
            .header("API-Key", apiKey)
            .header("User-Agent", "HypixelApi-WrapperTest")
            .exchange()
            .expectStatus().isOk
            .expectBody(Skills::class.java).value {
                val exp = it?.combat?.experience
                assert(exp != null && exp > 0)
            }
    }

}
