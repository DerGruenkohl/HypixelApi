package com.dergruenkohl.hypixelapi.client.config

import com.dergruenkohl.hypixelapi.client.data.HypixelPlayerReply
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfile
import com.dergruenkohl.hypixelapi.client.data.SkyblockProfiles
import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class CacheConfig {
    @Bean
    fun stringCache(): Cache<String, String> = Caffeine.newBuilder()
        .expireAfterWrite(Duration.ofMinutes(5))
        .maximumSize(1000)
        .build()
    @Bean
    fun skyblockProfilesCache(): Cache<String, SkyblockProfiles> = Caffeine.newBuilder()
        .expireAfterWrite(Duration.ofMinutes(5))
        .maximumSize(1000)
        .build()
    @Bean
    fun skyblockProfileCache(): Cache<String, SkyblockProfile> = Caffeine.newBuilder()
        .expireAfterWrite(Duration.ofMinutes(5))
        .maximumSize(1000)
        .build()
    @Bean
    fun hypixelPlayerCache(): Cache<String, HypixelPlayerReply> = Caffeine.newBuilder()
        .expireAfterWrite(Duration.ofMinutes(5))
        .maximumSize(1000)
        .build()
}
