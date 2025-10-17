package com.dergruenkohl.hypixelapi.services

interface BestiaryService {
    suspend fun getBestiary()
    suspend fun getBestiaryByType(type: String)
}