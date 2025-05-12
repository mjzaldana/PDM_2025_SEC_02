package com.example.pagging3test.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagging3test.model.Item
import kotlinx.coroutines.delay
import kotlin.math.min

class LocalPagingSource(private val items: List<Item>) : PagingSource<Int, Item>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item>{
        //Esto no es necesario, es solo para la prueba
        delay(1500)
        //Quitar el delay
        val page = params.key ?: 0
        val pageSize = params.loadSize

        val fromIndex = page * pageSize
        val toIndex = min(fromIndex + pageSize, items.size)

        return if(fromIndex < items.size){
            LoadResult.Page(
                data = items.subList(fromIndex, toIndex),
                prevKey = if(page == 0) null else page - 1,
                nextKey = if(toIndex < items.size) page + 1 else null
            )
        }else{
            LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? = null
}