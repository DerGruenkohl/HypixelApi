package com.dergruenkohl.hypixelapi.client.data

import io.swagger.v3.oas.annotations.media.Schema

@Schema(hidden = true)
data class SkyblockProfiles(
    val success: Boolean,
    val profiles: List<SkyblockProfile>
)
