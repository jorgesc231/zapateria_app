package com.example.phonenew.di

import android.content.Context
import androidx.room.Room
import com.example.la_unica.data.database.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DATABASE_NAME = "shoes_db"

    @Singleton
    @Provides
    fun provideRoom (@ApplicationContext context : Context) = Room.databaseBuilder(context, ProductDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideProductDao(db : ProductDatabase) = db.getProductDao()
}