package com.example.data

import com.example.domain.ApiResponse
import com.example.domain.Issue

object Mapper {
    fun ApiResponse<List<IssueResponse>>.toDomain(): ApiResponse<List<Issue>> {
        return when (this) {
            is ApiResponse.Success -> ApiResponse.Success(data.map { it.toDomain() })
            is ApiResponse.Failure -> ApiResponse.Failure(code)
        }
    }
}
