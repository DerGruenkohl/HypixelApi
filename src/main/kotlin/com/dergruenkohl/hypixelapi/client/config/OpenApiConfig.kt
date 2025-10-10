package com.dergruenkohl.hypixelapi.client.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import java.net.URI

@Configuration
class OpenApiConfig : WebFluxConfigurer {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(Info()
                .title("Hypixel API")
                .version("1.0")
                .description("Hypixel Skyblock API Wrapper"))
    }

    @Bean
    fun indexRedirect(): RouterFunction<ServerResponse> = router {
        GET("/") {
            ServerResponse.permanentRedirect(URI.create("/swagger-ui.html")).build()
        }
    }
}
