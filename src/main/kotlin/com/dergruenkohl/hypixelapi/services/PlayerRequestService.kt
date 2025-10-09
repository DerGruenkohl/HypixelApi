package com.dergruenkohl.hypixelapi.services


interface PlayerRequestService {
    /** Gets the Hypixel API Player Data by UUID. */
    suspend fun getPlayerData(uuid: String, apiKey: String): String
}