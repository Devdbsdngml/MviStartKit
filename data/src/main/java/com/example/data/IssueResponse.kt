package com.example.data

import com.example.domain.Issue
import com.google.gson.annotations.SerializedName

data class IssueResponse(
    @SerializedName("number")
    val number: Int,
    @SerializedName("title")
    val title: String,
)

fun IssueResponse.toDomain(): Issue {
    return Issue(
        number,
        title,
    )
}
