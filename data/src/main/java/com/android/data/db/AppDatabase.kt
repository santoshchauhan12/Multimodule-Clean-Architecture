package com.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.data.dao.ProductDao
import com.android.data.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getProductDao(): ProductDao
}