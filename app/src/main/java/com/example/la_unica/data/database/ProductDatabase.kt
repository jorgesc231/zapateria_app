package com.example.la_unica.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.la_unica.data.database.dao.ProductDao
import com.example.la_unica.data.database.entities.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun getProductDao() : ProductDao
}