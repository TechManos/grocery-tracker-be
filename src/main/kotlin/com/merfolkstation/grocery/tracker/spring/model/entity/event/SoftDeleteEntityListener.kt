package com.merfolkstation.grocery.tracker.spring.model.entity.event

import com.merfolkstation.grocery.tracker.spring.model.entity.SoftDeletable
import jakarta.persistence.PreRemove
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class SoftDeleteEntityListener {

    @PreRemove
    fun preRemove(entity: Any) {
        if (entity is SoftDeletable) {
            // Instead of actually removing, mark as deleted
            entity.deletedAt = LocalDateTime.now()
        }
    }
}