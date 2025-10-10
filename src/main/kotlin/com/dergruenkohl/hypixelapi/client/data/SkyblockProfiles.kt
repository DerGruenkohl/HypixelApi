package com.dergruenkohl.hypixelapi.client.data

import io.swagger.v3.oas.annotations.media.Schema
import kotlinx.serialization.Serializable

@Schema(hidden = true)
@Serializable
data class SkyblockProfiles(
    val success: Boolean,
    val profiles: List<SkyblockProfile>
)
