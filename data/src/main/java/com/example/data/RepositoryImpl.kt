package com.example.data

import com.example.data.Mapper.toDomain
import com.example.domain.ApiResponse
import com.example.domain.Issue
import com.example.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl
    @Inject
    constructor(
        private val dataSource: DataSource,
    ) : Repository {
        override fun getIssueList(
            owner: String,
            repo: String,
        ): Flow<ApiResponse<List<Issue>>> {
            return dataSource.getIssues(owner, repo).map { it.toDomain() }
        }
    }
