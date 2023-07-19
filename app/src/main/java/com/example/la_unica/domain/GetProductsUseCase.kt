package com.example.la_unica.domain

import com.example.la_unica.data.ProductRepository
import com.example.la_unica.data.database.entities.toDatabase
import com.example.la_unica.domain.model.Product
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke() : List<Product> {
        val localProducts = repository.getAllProductsFromDatabase()

        return if (localProducts.isNotEmpty()) {
            localProducts
        } else {
            val remoteProducts = repository.getAllProductsFromApi()
            repository.insertProducts(remoteProducts.map { it.toDatabase() })
            remoteProducts
        }
    }
}