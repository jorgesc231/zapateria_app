package com.example.la_unica.data.network

import com.example.la_unica.data.model.ProductModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiClient {
    @GET("/shoes")
    suspend fun getAllProducts() : Response<List<ProductModel>>

    @GET("/shoes/{id}")
    suspend fun getProductDetail(@Path("id") productId : Int) : Response<ProductModel>
}