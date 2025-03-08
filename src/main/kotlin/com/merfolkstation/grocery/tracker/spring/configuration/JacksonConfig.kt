package com.merfolkstation.grocery.tracker.spring.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class JacksonConfig {

    @Bean
    @Primary
    fun objectMapper(objectMapper: ObjectMapper): ObjectMapper {
        val module = Hibernate6Module()
        // Configure to handle lazy loading gracefully
        module.configure(Hibernate6Module.Feature.FORCE_LAZY_LOADING, false)
        objectMapper.registerModule(module)

        return objectMapper
    }
}