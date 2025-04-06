package com.android.network

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


suspend fun <T, S>safeApiCall(apiCall:suspend ()-> T,
                              onSuccessTransform: (T)-> S): ResponseState<S, String> {

   return  withContext(Dispatchers.IO) {
        val response = apiCall.invoke()

       Log.d("response=======", "recived === ${response!!::class.simpleName}")
        if(response is Response<*>) {
            Log.d("response=======", "check === ${response!!::class.simpleName}")

            if(response.isSuccessful) {
                Log.d("response=======", "onSuccess === ${response}")

                ResponseState.Success(onSuccessTransform.invoke(response))
            } else {
                Log.d("response=======", "failureee === ${response}")

                ResponseState.Failure(response.message())
            }
        } else {
            Log.d("response=======", "underrr === ${response}")

            ResponseState.Success(onSuccessTransform.invoke(response))
        }
    }
}