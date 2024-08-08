package com.example.data

import com.example.data.remote.Api
import com.example.domain.ApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DataSource {
    fun getIssues(
        owner: String,
        repo: String,
    ): Flow<ApiResponse<List<IssueResponse>>>
}

class DataSourceImpl
    @Inject
    constructor(
        private val api: Api,
        private val dispatcher: DispatcherProvider,
    ) : BaseDataSource(), DataSource {
        override fun getIssues(
            owner: String,
            repo: String,
        ): Flow<ApiResponse<List<IssueResponse>>> {
            return safeApiCallFlow(dispatcher) {
                api.getIssues(owner, repo)
            }
        }
    }
