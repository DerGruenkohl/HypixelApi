package com.dergruenkohl.hypixelapi.services.impl

import com.dergruenkohl.hypixelapi.services.UUIDService
import com.github.benmanes.caffeine.cache.Cache
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.springframework.stereotype.Service

@Service
class UUIDServiceImpl(
    private val client: HttpClient,
    private val httpCache: Cache<String, String>
): UUIDService {

    override suspend fun getUUID(name: String): String {
        val key = "uuid:$name"
        return httpCache.getIfPresent(key) ?: run {
            val uuid = client.get("https://mowojang.matdoes.dev/${name}")
                .body<UUIDService.UUIDResponse>().id
            httpCache.put(key, uuid)
            uuid
        }
    }

    override suspend fun getName(uuid: String): String {
        val key = "name:$uuid"
        return httpCache.getIfPresent(key) ?: run {
            val name = client.get("https://mowojang.matdoes.dev/${uuid}")
                .body<UUIDService.UUIDResponse>().name
            httpCache.put(key, name)
            name
        }
    }
}
