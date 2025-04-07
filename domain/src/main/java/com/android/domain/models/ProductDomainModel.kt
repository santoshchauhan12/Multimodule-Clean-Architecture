package com.android.domain.models

data class ProductDomainModel(
    val id: String,
    val title: String?= null,
    val thumbnail: String?= null
)