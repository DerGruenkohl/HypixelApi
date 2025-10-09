package com.dergruenkohl.hypixelapi.client.data

import com.fasterxml.jackson.annotation.JsonProperty

data class SkyblockProfile(
    val success: Boolean = true,
    @JsonProperty("profile_id")
    val profileId: String = "ProfileId not found",
    @JsonProperty("created_at")
    val createdAt: Long = -1L,
    @JsonProperty("cute_name")
    val cuteName: String = "cute_name not found",
    val selected: Boolean = false
)
