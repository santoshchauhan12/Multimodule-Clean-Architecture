package com.android.data.module

import android.content.Context
import androidx.room.Room
import com.android.data.dao.ProductDao
import com.android.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context) : AppDatabase {

        return Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()
    }

    @Provides
    @Singleton
    fun providesProductDao(db: AppDatabase): ProductDao {
        return db.getProductDao()
    }
}