package com.example.domain

import kotlinx.coroutines.flow.Flow

class GetIssueListUseCase(
    private val repository: Repository,
) {
    operator fun invoke(
        owner: String,
        repo: String,
    ): Flow<ApiResponse<List<Issue>>> = repository.getIssueList(owner, repo)
}
