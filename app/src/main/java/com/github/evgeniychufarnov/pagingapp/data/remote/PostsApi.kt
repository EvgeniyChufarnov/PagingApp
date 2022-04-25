package com.github.evgeniychufarnov.pagingapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostsApi {
    @GET("r/{subreddit}/hot.json")
    suspend fun getPosts(
        @Path("subreddit") subreddit: String,
        @Query("after") after: String,
    ): PostsResponse
}