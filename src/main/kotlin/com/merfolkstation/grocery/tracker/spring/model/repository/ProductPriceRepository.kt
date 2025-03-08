package com.merfolkstation.grocery.tracker.spring.model.repository

import com.merfolkstation.grocery.tracker.spring.model.entity.ProductPrice
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "product-prices", path = "product-prices")
interface ProductPriceRepository : SoftDeleteRepository<ProductPrice, Long>