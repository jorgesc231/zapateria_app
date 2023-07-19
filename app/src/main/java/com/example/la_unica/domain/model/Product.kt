package com.example.la_unica.domain.model

import com.example.la_unica.data.database.entities.ProductEntity
import com.example.la_unica.data.model.ProductModel

data class Product(
    val id : Int = 0,
    val name : String,
    val origen : String,
    val image : String,
    val marca : String,
    val size : Int,

    val price : Int?,
    val delivery : Boolean?,
)

fun ProductModel.toDomain() = Product(id, name, origen, image, marca, size, price, delivery)
fun ProductEntity.toDomain() = Product(id, name, origen, image, marca, size, price, delivery)