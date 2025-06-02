package com.example.pagging3test.repository

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pagging3test.data.api.JsonPlaceholderApi
import com.example.pagging3test.data.paging.PostPagingSource
import com.example.pagging3test.model.NewPost
import com.example.pagging3test.model.Post
import kotlinx.coroutines.flow.Flow

class PostRepository{
    private val api = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JsonPlaceholderApi::class.java)

    fun getPosts(): Flow<PagingData<Post>>{
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { PostPagingSource(api) }
        ).flow
    }

    suspend fun createPost(newPost: NewPost): Post{
        val response = api.createPost(newPost)

        if(response != null){
            Log.d("POST", "Post creado correctamente: ${response.id}")
        }else{
            Log.e("POST", "El post ha fallado")
        }
        return response
    }
}