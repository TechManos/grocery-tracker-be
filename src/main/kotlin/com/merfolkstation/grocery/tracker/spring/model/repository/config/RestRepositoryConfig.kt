package com.merfolkstation.grocery.tracker.spring.model.repository.config

import com.merfolkstation.grocery.tracker.spring.model.entity.*
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry

@Configuration
class RestRepositoryConfig : RepositoryRestConfigurer {
    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration, cors: CorsRegistry) {
        // We will manipulate on FE with the data, so we need it
        config.exposeIdsFor(
            Barcode::class.java,
            Product::class.java,
            ProductCategory::class.java,
            ProductPrice::class.java,
            Shop::class.java,
        )
    }
}