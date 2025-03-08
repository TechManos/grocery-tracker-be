package com.merfolkstation.grocery.tracker.spring.model.entity

import java.time.LocalDateTime

interface SoftDeletable {
    var deletedAt: LocalDateTime?

    fun isDeleted(): Boolean = deletedAt != null
}