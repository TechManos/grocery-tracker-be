package com.merfolkstation.grocery.tracker.spring.model.entity

import com.merfolkstation.grocery.tracker.spring.model.entity.event.SoftDeleteEntityListener
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "product")
@EntityListeners(SoftDeleteEntityListener::class)
class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column
    val description: String? = null,

    @Column(name = "standard_size", nullable = false)
    val standardSize: String,

    @Column(name = "deleted_at")
    override var deletedAt: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    var category: ProductCategory? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barcode_id", nullable = true)
    var barcode: Barcode? = null
) : SoftDeletable