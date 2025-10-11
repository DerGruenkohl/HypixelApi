package com.dergruenkohl.hypixelapi.controllers

import com.dergruenkohl.hypixelapi.services.CollectionService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Resources", description = "Get static resources currently only collection data")
class ResourcesController(private val collectionService: CollectionService) {

    @GetMapping("/v1/resources/collections")
    suspend fun getCollections() = collectionService.retrieveCollectionData()
}