package com.android.data.network

import com.android.data.models.ProductListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/products")
    suspend fun getProductList(): Response<ProductListResponse>
}