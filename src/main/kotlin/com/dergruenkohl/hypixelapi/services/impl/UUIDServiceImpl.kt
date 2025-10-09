package com.dergruenkohl.hypixelapi.services.impl

import com.dergruenkohl.hypixelapi.services.UUIDService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.springframework.stereotype.Service

@Service
class UUIDServiceImpl(private val client: HttpClient): UUIDService {
    override suspend fun getUUID(name: String): String {
        return client.get("https://mowojang.matdoes.dev/${name}").body<UUIDService.UUIDResponse>().id
    }

    override suspend fun getName(uuid: String): String {
        return client.get("https://mowojang.matdoes.dev/${uuid}").body<UUIDService.UUIDResponse>().name
    }

}