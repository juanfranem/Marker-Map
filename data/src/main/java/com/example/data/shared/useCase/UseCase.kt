package com.example.data.shared.useCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<out Type, in Params> where Type: Any? {

    abstract suspend fun execute(params: Params): Type

    suspend operator fun invoke(
        params: Params,
        onResult: (Type) -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        try {
            val result = withContext(Dispatchers.IO) {
                execute(params)
            }
            onResult(result)
        } catch (e: Exception) {
            onError(e)
        }

    }
}