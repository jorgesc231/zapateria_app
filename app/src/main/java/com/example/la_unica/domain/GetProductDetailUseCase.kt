package com.example.la_unica.domain

import com.example.la_unica.data.ProductRepository
import com.example.la_unica.data.database.entities.toDatabase
import com.example.la_unica.domain.model.Product
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke(product_id : Int) : Product {
        val localProduct = repository.getProductFromDatabase(product_id)

        return if (localProduct.price != null && localProduct.delivery != null) {
            localProduct
        } else {
            val remoteProduct = repository.getProductFromApi(product_id)
            repository.insertProduct(remoteProduct.toDatabase())
            remoteProduct
        }
    }
}