package com.android.data.repository

import android.util.Log
import com.android.data.dao.ProductDao
import com.android.data.entity.ProductEntity
import com.android.data.models.Product
import com.android.data.models.ProductListResponse
import com.android.data.network.ApiService
import com.android.domain.models.ProductDomainModel
import com.android.domain.repository.IProductListRepository
import com.android.network.IFailure
import com.android.network.NetworkHelper
import com.android.network.ResponseState
import com.android.network.safeApiCall
import java.lang.Exception
import javax.inject.Inject

const val CACHE_STALE = 60 * 60 * 1000L

class ProductListRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao,
    private val networkHelper: NetworkHelper
) : IProductListRepository {


    override suspend fun getProductList(): ResponseState<List<ProductDomainModel>, IFailure> {

        if (networkHelper.isNetworkConnected().not()){
            val response = productDao.getProducts().map { it.toDomain() }
            return ResponseState.Success(response)
        }

        return try {
            if (isCacheStale().not()) {
                val response = productDao.getProducts().map { it.toDomain() }
                if(response.isEmpty().not()) {
                    Log.d("cacheee===", "response===== ${response}")
                    ResponseState.Success(response)
                }else {
                    getFromRemote()
                }
            } else {
                getFromRemote()
            }
        }catch (e: Exception) {
            val response =  productDao.getProducts().map { it.toDomain() }
            ResponseState.Success(response)
        }
    }

    private suspend fun getFromRemote(): ResponseState<MutableList<ProductDomainModel>, IFailure> {
       return safeApiCall(apiCall = {
            apiService.getProductList()
        }, onSuccessTransform = {
            val entityResponse = it.body()?.productDataToDomain()?.map { res-> res.toEntity() }
            if (entityResponse != null) {
                productDao.saveProducts(entityResponse)
            }
            it.body()?.productDataToDomain() ?: mutableListOf()
        })
    }

    override suspend fun saveProductList(productList: List<ProductDomainModel>) {

        val list = productList.map { it.toEntity() }
        productDao.saveProducts(list)
    }

    override suspend fun getProductFromCache(): List<ProductDomainModel> {
        return productDao.getProducts().map { it.toDomain() }
    }

    fun isCacheStale(): Boolean {
        val lastUpdated = productDao.getProducts().maxOfOrNull { it.lastUpdated } ?: 0L
        return System.currentTimeMillis() - lastUpdated > CACHE_STALE
    }
}


private fun ProductListResponse.productDataToDomain(): MutableList<ProductDomainModel> {

    return this.products?.map { ProductDomainModel(it.id, it.title, it.thumbnail) }?.toMutableList()
        ?: mutableListOf()
}

private fun ProductEntity.toDomain(): ProductDomainModel = ProductDomainModel(id, title, thumbnail)
private fun ProductDomainModel.toEntity(): ProductEntity = ProductEntity(id, title, thumbnail)