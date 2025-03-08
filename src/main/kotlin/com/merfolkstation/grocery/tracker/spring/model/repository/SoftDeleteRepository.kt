package com.merfolkstation.grocery.tracker.spring.model.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.rest.core.annotation.RestResource
import java.util.*

@NoRepositoryBean
interface SoftDeleteRepository<T, ID> : JpaRepository<T, ID> {

    @RestResource(path = "active", rel = "active")  // Custom path to avoid ambiguity
    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NULL")
    fun findActive(): List<T>

    @RestResource(path = "active-paged", rel = "active-paged")  // Custom path for paged version
    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NULL")
    fun findActive(pageable: Pageable): Page<T>

    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id AND e.deletedAt IS NULL")
    override fun findById(id: ID & Any): Optional<T>

    // Add methods to access deleted entities if needed
    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NOT NULL")
    @RestResource(path = "deleted", rel = "deleted")
    fun findDeleted(): List<T>
}