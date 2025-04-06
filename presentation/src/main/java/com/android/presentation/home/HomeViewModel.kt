package com.android.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.interactor.GetProductUseCase
import com.android.domain.models.ProductDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {

    private val _productStateFlow = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    var productStateFlow = _productStateFlow
    fun getProductList(){
        viewModelScope.launch(Dispatchers.IO) {
            _productStateFlow.emit(ProductUiState.Loading)
            getProductUseCase.execute(onSuccess = {
                    _productStateFlow.emit(ProductUiState.Product(it))
            }, onFailure = {
                    _productStateFlow.emit(ProductUiState.Failure(it))
            }, viewModelScope)
        }


    }

}

sealed class ProductUiState {

    object Loading : ProductUiState()

    data class Product(val data: List<ProductDomainModel>) : ProductUiState()

    data class Failure(val errorMsg: String) : ProductUiState()
}