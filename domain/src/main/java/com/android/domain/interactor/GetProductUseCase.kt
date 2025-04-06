package com.android.domain.interactor

import com.android.domain.models.ProductDomainModel
import com.android.domain.repository.IProductListRepository
import com.android.network.ResponseState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductUseCase @Inject constructor(val productRepo: IProductListRepository): BaseUseCase<List<ProductDomainModel>, String>(){
    override suspend fun run(): ResponseState<List<ProductDomainModel>, String> {

        val responseState = productRepo.getProductList()
        return responseState
    }
//    fun execute() = flow<List<ProductDomainModel>> {
//        val responseState = productRepo.getProductList()
//
//        emit(responseState.)
//    }
}