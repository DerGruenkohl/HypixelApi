package com.dergruenkohl.hypixelapi.services

import com.dergruenkohl.hypixelapi.client.data.HypixelPlayer
import com.dergruenkohl.hypixelapi.client.data.HypixelPlayerReply


interface PlayerRequestService {
    /** Gets the Hypixel API Player Data by UUID. */
    suspend fun getPlayerData(uuid: String, apiKey: String, userAgent: String): HypixelPlayer
}