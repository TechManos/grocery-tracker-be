package com.merfolkstation.grocery.tracker.spring.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "barcode")
class Barcode(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val value: String,

    @Column(nullable = false)
    val type: String,

    @OneToMany(mappedBy = "barcode", fetch = FetchType.LAZY)
    val products: MutableSet<Product> = mutableSetOf()
)