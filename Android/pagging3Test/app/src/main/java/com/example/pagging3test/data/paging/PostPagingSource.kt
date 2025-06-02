package com.example.pagging3test.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagging3test.data.api.JsonPlaceholderApi
import com.example.pagging3test.model.Item
import com.example.pagging3test.model.Post
import kotlinx.coroutines.delay
import kotlin.math.min

class PostPagingSource(
    private val api: JsonPlaceholderApi
): PagingSource<Int, Post>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post>{
        return try{
            val page = params.key?: 1
            val pageSize = params.loadSize

            val response = api.getPosts(page, pageSize)

            LoadResult.Page(
                data = response,
                prevKey = if(page==1) null else page - 1,
                nextKey = if(response.isEmpty()) null else page + 1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? = null
}
