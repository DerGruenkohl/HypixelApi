package com.dergruenkohl.hypixelapi.services

import kotlinx.serialization.Serializable

interface UUIDService {
    @Serializable
    data class UUIDResponse(val id: String, val name: String)


    suspend fun getUUID(name: String): String
    suspend fun getName(uuid: String): String
}