package com.dergruenkohl.hypixelapi.controllers

import com.dergruenkohl.hypixelapi.services.CollectionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ResourcesController(private val collectionService: CollectionService) {

    @GetMapping("/resources/collections")
    suspend fun getCollections() = collectionService.retrieveCollectionData()
}