package com.example.la_unica.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.la_unica.domain.model.Product

@Entity(tableName = "products_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name = "nombre") val name : String,
    @ColumnInfo(name = "origen") val origen : String,
    @ColumnInfo(name = "imagenLink") val image : String,
    @ColumnInfo(name = "marca") val marca : String,
    @ColumnInfo(name = "numero") val size : Int,

    @ColumnInfo(name = "precio") val price : Int?,
    @ColumnInfo(name = "entrega") val delivery : Boolean?,
)

fun Product.toDatabase() = ProductEntity(id, name, origen, image, marca, size, price, delivery)