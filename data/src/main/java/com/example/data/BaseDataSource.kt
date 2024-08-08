package com.example.data

import com.example.domain.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeoutOrNull
import retrofit2.Response

abstract class BaseDataSource {
    fun <T> safeApiCallFlow(
        dispatcher: DispatcherProvider,
        call: suspend () -> Response<T>,
    ): Flow<ApiResponse<T>> =
        flow {
            withTimeoutOrNull(10000L) {
                val response = call()

                try {
                    if (response.isSuccessful) {
                        response.body()?.let { data ->
                            emit(ApiResponse.Success(data))
                        }
                    } else {
                        response.errorBody()?.let { error ->
                            error.close()
                            // todo fail
                        }
                    }
                } catch (e: Exception) {
                    // todo catch
                }
            } ?: emit(ApiResponse.Failure("TIMEOUT"))
        }.flowOn(dispatcher.io)
}
