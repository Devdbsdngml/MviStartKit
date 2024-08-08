package com.example.domain

sealed class ApiResponse<out T> {
    data class Success<out T>(
        val data: T,
    ) : ApiResponse<T>()

    data class Failure(
        val code: String?,
    ) : ApiResponse<Nothing>()
}
