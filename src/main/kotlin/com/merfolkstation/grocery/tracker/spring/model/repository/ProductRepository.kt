package com.merfolkstation.grocery.tracker.spring.model.repository

import com.merfolkstation.grocery.tracker.spring.model.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
interface ProductRepository : JpaRepository<Product, Long>