package com.merfolkstation.grocery.tracker.spring.model.repository

import com.merfolkstation.grocery.tracker.spring.model.entity.SoftDeletable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.rest.core.annotation.RestResource
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert
import java.time.LocalDateTime
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

    @Transactional
    override fun deleteById(id: ID & Any) {
        Assert.notNull(id, "The given id must not be null")
        findById(id).ifPresent { entity ->
            if (entity is SoftDeletable) {
                entity.deletedAt = LocalDateTime.now()
                save(entity)
            } else {
                delete(entity)
            }
        }
    }

    @Transactional
    override fun delete(entity: T & Any) {
        Assert.notNull(entity, "Entity must not be null")
        if (entity is SoftDeletable) {
            entity.deletedAt = LocalDateTime.now()
            save(entity)
        } else {
            throw UnsupportedOperationException("Entity of type ${entity.javaClass.simpleName} does not support soft deletion, although \"soft\" repository is used")
        }
    }

    @Transactional
    override fun deleteAll() {
        findAll().filterNotNull().forEach { item ->
            delete(item)
        }
    }

    @Transactional
    override fun deleteAll(entities: MutableIterable<T>) {
        entities.filterNotNull().forEach { entity ->
            delete(entity)
        }
    }

    @Transactional
    override fun deleteAllById(ids: MutableIterable<ID>) {
        ids.filterNotNull().forEach { id -> deleteById(id) }
    }
}