package com.example.la_unica.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.la_unica.data.database.entities.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM products_table")
    suspend fun getAllProducts() : List<ProductEntity>

    @Query("SELECT * FROM products_table WHERE id = :id LIMIT 1")
    suspend fun getProduct(id : Int) : ProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products : List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product : ProductEntity)

    @Update
    suspend fun updateProduct(product : ProductEntity)

    @Delete
    suspend fun deleteProduct(product : ProductEntity)

    @Query("DELETE FROM products_table")
    suspend fun deleteAllProducts()
}