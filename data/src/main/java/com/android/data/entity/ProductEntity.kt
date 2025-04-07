package com.android.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
class ProductEntity(
    @PrimaryKey
    val id: String,
    val title: String?= null,
    val thumbnail: String?= null,
    val lastUpdated: Long = System.currentTimeMillis()
)