package com.merfolkstation.grocery.tracker.spring.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "shop")
class Shop(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(nullable = false)
    val name: String,

    @Column
    val description: String? = null,

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    val productPrices: MutableSet<ProductPrice> = mutableSetOf()
)