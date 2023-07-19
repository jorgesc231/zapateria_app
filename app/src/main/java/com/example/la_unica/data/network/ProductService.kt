package com.example.la_unica.data.network

import com.example.la_unica.data.model.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


    class ProductService @Inject constructor(private val api:ProductApiClient) {

        suspend fun getProducts() : List<ProductModel> {
            return withContext(Dispatchers.IO) {
                val response = api.getAllProducts()
                response.body() ?: emptyList()
            }
        }

        suspend fun getProductDetail(id : Int) : ProductModel {
            return withContext(Dispatchers.IO) {
                val response = api.getProductDetail(id)
                response.body() ?: ProductModel(-1, "", "", "", "", 0, 0, null)
            }
        }
    }
