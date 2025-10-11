package com.dergruenkohl.hypixelapi.client.data

import io.swagger.v3.oas.annotations.media.Schema
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Schema(hidden = true)
@Serializable
data class SkyblockProfile(
    val success: Boolean = true,
    @SerialName("profile_id")
    val profileId: String = "ProfileId not found",
    @SerialName("created_at")
    val createdAt: Long = -1L,
    @SerialName("cute_name")
    val cuteName: String = "cute_name not found",
    val selected: Boolean = false,
    val members: Map<String, SkyblockProfileMember> = emptyMap(),
    val cause: String? = null
)
