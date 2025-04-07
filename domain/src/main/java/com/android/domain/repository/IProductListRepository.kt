package com.android.domain.repository

import com.android.domain.models.ProductDomainModel
import com.android.network.IFailure
import com.android.network.ResponseState

interface IProductListRepository {

   suspend fun getProductList(): ResponseState<List<ProductDomainModel>, IFailure>

   suspend fun saveProductList(product: List<ProductDomainModel>)

   suspend fun getProductFromCache(): List<ProductDomainModel>
}