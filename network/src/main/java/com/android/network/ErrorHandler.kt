package com.android.network


sealed class ResponseState<out T, out S> {

    data class Success<T>(val successVal: T): ResponseState<T, Nothing>()

    data class Failure<S>(val error: S): ResponseState<Nothing, S>()

    fun either(onSuccessCallback:(T)-> Unit, onFailureCallback: (S)-> Unit) {

      when(this) {

            is Success -> {
                onSuccessCallback.invoke(this.successVal)

            }

            is Failure -> {
                onFailureCallback.invoke(this.error)
            }
        }
    }
}

interface IFailure

sealed class ErrorModel: IFailure {

    data class HttpFailure(val msg: String) : ErrorModel()
}