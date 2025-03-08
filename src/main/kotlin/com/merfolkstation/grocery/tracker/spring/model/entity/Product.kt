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
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column
    val description: String? = null,

    @Column(name = "standard_size", nullable = false)
    val standardSize: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    val category: ProductCategory,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barcode_id")
    var barcode: Barcode? = null,

    @Column(name = "deleted_at")
    override var deletedAt: LocalDateTime? = null
) : SoftDeletable