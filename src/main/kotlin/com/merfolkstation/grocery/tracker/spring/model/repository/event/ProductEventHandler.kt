package com.merfolkstation.grocery.tracker.spring.model.repository.event

import com.merfolkstation.grocery.tracker.spring.model.entity.Product
import com.merfolkstation.grocery.tracker.spring.model.repository.BarcodeRepository
import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.HandleBeforeSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@RepositoryEventHandler
class ProductEventHandler(private val barcodeRepository: BarcodeRepository) {

    @HandleBeforeSave
    @Transactional
    fun handleProductUpdate(product: Product) {
        handleBarcodeDeduplication(product)
    }

    private fun handleBarcodeDeduplication(product: Product) {
        val barcode = product.barcode

        // Only process if there's a barcode with a value and a type being set that hasn't been saved yet
        if (barcode != null && barcode.id == null && barcode.value.isNotBlank() && barcode.type.isNotBlank()) {
            // Look for existing barcode with the same value and type
            val existingBarcode = barcodeRepository.findByValueAndType(barcode.value, barcode.type)

            // If found, use the existing barcode instead of creating a new one
            if (existingBarcode != null) {
                product.barcode = existingBarcode
            }
        }
    }
}