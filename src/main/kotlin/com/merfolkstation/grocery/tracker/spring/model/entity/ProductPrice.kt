package com.merfolkstation.grocery.tracker.spring.model.entity

import com.merfolkstation.grocery.tracker.spring.model.entity.event.SoftDeleteEntityListener
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "product_price")
@EntityListeners(SoftDeleteEntityListener::class)
class ProductPrice(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    var product: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    var shop: Shop? = null,

    @Column(nullable = false, precision = 10, scale = 2)
    val amount: BigDecimal,

    @Column(name = "currency_code", nullable = false, length = 3)
    val currencyCode: String,

    @Column(name = "date_recorded", nullable = false)
    val dateRecorded: LocalDateTime = LocalDateTime.now(),

    @Column(name = "deleted_at")
    override var deletedAt: LocalDateTime? = null
) : SoftDeletable