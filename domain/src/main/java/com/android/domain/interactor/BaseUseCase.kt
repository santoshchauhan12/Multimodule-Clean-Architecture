package com.android.domain.interactor

import com.android.network.ResponseState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseUseCase<T, S> {

    fun execute(onSuccess: suspend  (T)-> Unit,
                onFailure: suspend  (S)-> Unit,
                scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    ) {

        scope.launch {
            val result = run()
            when(result) {

                is ResponseState.Success -> {
                    withContext(Dispatchers.Main) {
                        onSuccess(result.successVal)
                    }
                }

                is ResponseState.Failure -> {
                    withContext(Dispatchers.Main) {
                        onFailure(result.error)
                    }
                }
            }
        }
    }

    abstract suspend  fun run(): ResponseState<T, S>
}