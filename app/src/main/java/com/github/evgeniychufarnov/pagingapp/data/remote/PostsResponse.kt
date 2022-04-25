package com.github.evgeniychufarnov.pagingapp.data.remote

import com.github.evgeniychufarnov.pagingapp.domain.entities.Post
import com.google.gson.annotations.SerializedName

data class PostsResponse(
    val data: PostData
)

data class PostData(
    val children: List<Child>,
)

data class Child(
    val data: Data
)

data class Data(
    val name: String,
    val title: String?,
    @SerializedName("num_comments") val commentsNum: Int?,
    @SerializedName("score") val rating: Int?,
)

fun PostsResponse.toPosts(): List<Post> {
    return data.children
        .map { it.data }
        .map {
            Post(
                it.name,
                it.title,
                it.rating,
                it.commentsNum
            )
        }
}