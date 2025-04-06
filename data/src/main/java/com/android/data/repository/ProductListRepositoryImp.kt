package com.android.data.repository

import com.android.data.models.ProductListResponse
import com.android.data.network.ApiService
import com.android.domain.models.ProductDomainModel
import com.android.domain.repository.IProductListRepository
import com.android.network.ResponseState
import com.android.network.safeApiCall
import javax.inject.Inject

class ProductListRepositoryImp @Inject constructor(private val apiService: ApiService) : IProductListRepository {
    override suspend fun getProductList(): ResponseState<List<ProductDomainModel>, String> {

      return  safeApiCall(apiCall =  {
            apiService.getProductList()
        }, onSuccessTransform = {
          it.body()?.productDataToDomain() ?: emptyList()
        })
    }
}


private fun ProductListResponse.productDataToDomain(): MutableList<ProductDomainModel> {

    var productDomainList = mutableListOf<ProductDomainModel>()
    this.products?.forEach {
        productDomainList.add(ProductDomainModel(it.id, it.title, it.thumbnail))
    }

    return productDomainList
}