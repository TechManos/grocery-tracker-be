package com.merfolkstation.grocery.tracker.spring.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "product_category")
class ProductCategory(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(nullable = false, unique = true)
    val name: String,

    @Column
    val description: String? = null,
) {

    // LAZY is default for OneToMany, but just to be clear
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    val products: Set<Product> = setOf()
}