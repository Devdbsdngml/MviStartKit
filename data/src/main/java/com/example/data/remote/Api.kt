package com.example.data.remote

import com.example.data.IssueResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("/repos/{owner}/{repo}/issues")
    fun getIssues(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Response<List<IssueResponse>>
}
