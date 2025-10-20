package com.dergruenkohl.hypixelapi.client.config

import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class TrailingSlashHandler: WebFilter {
    override fun filter(
        exchange: ServerWebExchange,
        chain: WebFilterChain
    ): Mono<Void> {
        val path = exchange.request.uri.path
        if (path.endsWith("/") && path.length > 1) {
            val newPath = path.dropLast(1)
            val request = exchange.request.mutate().path(newPath).build()
            return chain.filter(exchange.mutate().request(request).build())
        }
        return chain.filter(exchange)
    }


}