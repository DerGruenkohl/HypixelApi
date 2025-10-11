package com.dergruenkohl.hypixelapi.client.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HypixelPlayerReply(
    val success: Boolean,
    val player: HypixelPlayer = HypixelPlayer(),
    val cause: String? = null
)
@Serializable
data class HypixelPlayer(
    val uuid: String = "",
    val firstLogin: Long = -1L,
    val lastLogin: Long = -1L,
    val achievementsOneTime: List<String> = emptyList(),
    val achievements: Map<String, Int> = emptyMap(),
    val lastLogout: Long = -1L,
    val petConsumables: Map<String, Int> = emptyMap(),
    val socialMedia: PlayerSocialMedia = PlayerSocialMedia(),


)
@Serializable
data class PlayerSocialMedia(
    val links: PlayerSocialMediaLinks = PlayerSocialMediaLinks(),
    val prompt: Boolean = false
)
@Serializable
data class PlayerSocialMediaLinks(
    @SerialName("DISCORD")
    val discord: String = "Linked Discord not found"
)
