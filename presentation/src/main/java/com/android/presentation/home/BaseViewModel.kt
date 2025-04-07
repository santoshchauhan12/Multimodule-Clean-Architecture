package com.android.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.interactor.BaseUseCase
import com.android.network.ErrorModel
import com.android.network.IFailure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(){

    private val _errorStateFlow = MutableStateFlow<IFailure?> (null)

    val errorStateFlow = _errorStateFlow
    fun <Params, Type> BaseUseCase<Type>.invoke(params: Params,
                                          onSuccess: suspend (Type)-> Unit,
                                          onFailure: suspend (String)-> Unit) {

        this.execute(onSuccess, onFailure = {

            viewModelScope.launch(Dispatchers.IO) {
                errorStateFlow.emit(it)
            }
        })

    }
}