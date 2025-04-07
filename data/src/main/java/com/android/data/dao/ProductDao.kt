package com.android.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.data.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("select *from products")
    fun getProducts(): List<ProductEntity>

    @Insert
    fun saveProducts(entityList: List<ProductEntity>)
}