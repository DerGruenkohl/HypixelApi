package com.dergruenkohl.hypixelapi.client.data

import com.fasterxml.jackson.annotation.JsonProperty

data class SkyblockProfileMember(
    @JsonProperty("player_id")
    val uuid: String,
)
