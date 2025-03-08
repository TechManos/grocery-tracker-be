package com.merfolkstation.grocery.tracker.spring.model.repository

import com.merfolkstation.grocery.tracker.spring.model.entity.Barcode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "barcodes", path = "barcodes")
interface BarcodeRepository : JpaRepository<Barcode, Long> {
    fun findByValueAndType(value: String, type: String): Barcode?
}