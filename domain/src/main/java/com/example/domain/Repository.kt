package com.example.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getIssueList(
        owner: String,
        repo: String,
    ): Flow<ApiResponse<List<Issue>>>
}
