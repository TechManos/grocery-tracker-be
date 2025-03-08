package com.merfolkstation.grocery.tracker.spring.model.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean
import java.util.*

@NoRepositoryBean
interface SoftDeleteRepository<T, ID> : JpaRepository<T, ID> {

    // Override default methods to filter deleted entities
    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NULL")
    override fun findAll(): List<T>

    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NULL")
    override fun findAll(pageable: Pageable): Page<T>

    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id AND e.deletedAt IS NULL")
    override fun findById(id: ID & Any): Optional<T>

    // Add methods to access deleted entities if needed
    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NOT NULL")
    fun findDeleted(): List<T>

    @Query("SELECT e FROM #{#entityName} e")
    fun findAllIncludingDeleted(): List<T>
}