package com.merfolkstation.grocery.tracker.spring.configuration

import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {

    @Bean
    fun hibernateModuleCustomizer(): Jackson2ObjectMapperBuilderCustomizer {
        return Jackson2ObjectMapperBuilderCustomizer { builder ->
            builder.modules(Hibernate6Module().apply {
                // Don't force lazy loading - let Jackson handle it
                configure(Hibernate6Module.Feature.FORCE_LAZY_LOADING, false)
                // Just use the entity ID for uninitialized proxies
                configure(Hibernate6Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS, true)
                // Better handling of Hibernate collection types
                configure(Hibernate6Module.Feature.REPLACE_PERSISTENT_COLLECTIONS, true)
                // Handle nulls properly
                configure(Hibernate6Module.Feature.USE_TRANSIENT_ANNOTATION, true)
            },
            // Add Java 8 date/time module - jsr310
            JavaTimeModule()
            )
        }
    }
}