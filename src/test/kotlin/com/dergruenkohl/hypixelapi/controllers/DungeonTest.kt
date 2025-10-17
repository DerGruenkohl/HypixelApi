
package com.dergruenkohl.hypixelapi.controllers

import com.dergruenkohl.hypixelapi.TestConfiguration
import com.dergruenkohl.hypixelapi.client.HttpClient
import com.dergruenkohl.hypixelapi.client.config.CacheConfig
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfile
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfileMember
import com.dergruenkohl.hypixelapi.data.DungeonCompletion
import com.dergruenkohl.hypixelapi.data.Dungeons
import com.dergruenkohl.hypixelapi.data.Skills
import com.dergruenkohl.hypixelapi.services.SkyblockService
import com.dergruenkohl.hypixelapi.services.UUIDService
import com.dergruenkohl.hypixelapi.services.impl.SkyblockServiceImpl
import com.dergruenkohl.hypixelapi.services.impl.UUIDServiceImpl
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.Duration

@WebFluxTest(controllers = [DungeonsController::class])
@ExtendWith(SpringExtension::class)
@Import(SkyblockServiceImpl::class, UUIDServiceImpl::class, HttpClient::class, CacheConfig::class, TestConfiguration::class)
class DungeonTest @Autowired constructor(
     webClient: WebTestClient,
     testConfig: TestConfiguration,
) {
    val webClient =  webClient.mutate()
        .responseTimeout(Duration.ofSeconds(10)).build()
    val apiKey = testConfig.apiKey
    init {

    }

    @Test
    fun testDungeonsEndpointReturnsDungeons() {
        webClient.get()
            .uri("/v1/dungeons/garaf")
            .header("API-Key", apiKey)
            .header("User-Agent", "HypixelApi-WrapperTest")
            .exchange()
            .expectStatus().isOk
            .expectBody(Dungeons::class.java)
    }
    @Test
    fun testSingleDungeonsEndpointReturnsDungeon() {
        webClient.get()
            .uri("/v1/dungeons/garaf/floor/f4")
            .header("API-Key", apiKey)
            .header("User-Agent", "HypixelApi-WrapperTest")
            .exchange()
            .expectStatus().isOk
            .expectBody(DungeonCompletion::class.java)
    }


}
