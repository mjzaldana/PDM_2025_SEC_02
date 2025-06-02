package com.example.pagging3test.data.api

import com.example.pagging3test.model.NewPost
import com.example.pagging3test.model.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface  JsonPlaceholderApi {
    @GET("posts")
    suspend fun getPosts(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): List<Post>

    @POST("posts")
    suspend fun createPost(
        @Body newPost: NewPost
    ):Post
}



